# Model Skeleton Hierarchy — Refactoring Guide

This document proposes a **nested skeleton hierarchy** for each of the 6 addon models,
following the pattern established by Grimoire of Gaia 1.20 models (e.g. `SuccubusModel`,
`BansheeModel`). It also describes the step-by-step workflow for refactoring each model.

> **Do NOT change textures or box dimensions.** Only part parenting and offsets change.

---

## Reference: GOG Nested-Body Pattern

The canonical hierarchy used by the upstream Grimoire of Gaia models is:

```
root (0, 24, 0)
├── bodybottom                         ← hip / pelvis pivot
│   ├── bodymiddle                     ← waist
│   │   ├── bodymiddlebutton
│   │   └── bodytop                    ← upper torso
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes       ← child of head: inherits rotation
│   │       │       ├── headaccessory  ← child of head: inherits rotation
│   │       │       └── (horns, hair, ears…)
│   │       ├── rightarm
│   │       │   └── rightarmlower
│   │       ├── leftarm
│   │       │   └── leftarmlower
│   │       ├── chest / rightchest / leftchest
│   │       └── (mantle, back accessories…)
│   ├── (waist / skirt / cloak chain…)
│   └── (tail chain…)
├── rightleg
│   └── (rightleglower / rightfoot…)
└── leftleg
    └── (leftleglower / leftfoot…)
```

### Why this matters

| Benefit | Explanation |
|---------|-------------|
| **Auto-inherited rotation** | `headeyes` / `headaccessory` as children of `head` inherit its yRot/xRot — no manual sync needed in `setupAnim()`. |
| **Anatomical transforms** | Rotating `bodytop` tilts the torso *and* everything above it (head, arms). Rotating `bodybottom` sways the hips and everything below (skirts, tail). |
| **Simpler `translateToHand()`** | With arms under `bodytop`, calling `getArm().translateAndRotate()` walks the correct chain. |
| **Consistency with GOG** | Eases future maintenance and makes models interchangeable with upstream patterns. |

---

## 1 · BaphometModel — Proposed Hierarchy

**Current**: all 11 animated parts are flat children of root (except head→horns/ears/hair, arm→hand).

```
baphomet (0, 24, 0)
│
├── bodybottom                              ← NEW pivot (hip area)
│   ├── bodymiddle
│   │   ├── bodymiddlebutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       │       ├── righthorn1–4, lefthorn1–4   (already under head ✓)
│   │       │       ├── rightear, leftear            (already under head ✓)
│   │       │       └── righthairclip, lefthairclip, righthair, lefthair (already under head ✓)
│   │       ├── rightarm
│   │       │   └── righthand                        (already under rightarm ✓)
│   │       ├── leftarm
│   │       │   └── lefthand                         (already under leftarm ✓)
│   │       ├── mantle                      ← MOVE from root → bodytop
│   │       └── mantlefront                 ← MOVE from root → bodytop
│   ├── waist                               ← MOVE from root → bodybottom
│   ├── cloak1                              ← MOVE from root → bodybottom
│   │   └── cloak2                          ← CHAIN (was flat sibling)
│   │       └── cloak3                      ← CHAIN (was flat sibling)
│   └── (bodybottom mesh: box from current bodybottom)
│
├── rightleg                                (stays under root ✓)
│   └── rightfoot                           (already under rightleg ✓)
└── leftleg                                 (stays under root ✓)
    └── leftfoot                            (already under leftleg ✓)
```

### Baphomet-specific notes

- `cloak1 → cloak2 → cloak3` should become a chain. Their `setupAnim()` Z-rotation
  values are already independent, so chaining them means each rotation accumulates on
  top of the parent's — you may want to adjust the rotation magnitudes.
- `mantle` and `mantlefront` follow the head yRot in some GOG models. Decide whether
  they should stay under `bodytop` (inherits torso rotation only) or under `neck`
  (inherits head rotation).

---

## 2 · DhampirModel — Proposed Hierarchy

**Current**: all 14 animated parts are flat children of root (except head→hats/hair, arm→pauldron, leg→boot).

```
dhampir (0, 24, 0)
│
├── bodybottom                              ← NEW pivot
│   ├── bodymiddle
│   │   ├── bodymiddlebutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       │       ├── righthair, lefthair          (already under head ✓)
│   │       │       └── hat1, hat2, hat3, hatflower  (already under head ✓)
│   │       ├── rightarm
│   │       │   └── rightpauldron                    (already under rightarm ✓)
│   │       ├── leftarm
│   │       │   └── leftpauldron                     (already under leftarm ✓)
│   │       ├── rightchest, leftchest       ← MOVE from root → bodytop
│   │       ├── mantle                      ← MOVE from root → bodytop (syncs yRot w/ head)
│   │       └── hair (back ponytail)        ← MOVE from root → bodytop or neck
│   ├── waist                               ← MOVE from root → bodybottom
│   ├── cape1                               ← MOVE from root → bodybottom
│   │   └── cape2                           ← CHAIN (was flat sibling)
│   └── (bodybottom mesh)
│
├── rightleg                                (stays under root ✓)
│   └── rightboot                           (already under rightleg ✓)
└── leftleg                                 (stays under root ✓)
    └── leftboot                            (already under leftleg ✓)
```

### Dhampir-specific notes

- `cape1 → cape2` should become a chain (currently flat siblings).
- `mantle.yRot = head.yRot` in `setupAnim()` — if `mantle` is a child of `neck`,
  it inherits head rotation and this line can be removed. If it stays under `bodytop`,
  you still need the manual sync.
- `hair` (back ponytail) currently syncs yRot to head. Making it a child of `neck`
  removes that sync need.

---

## 3 · GorgonModel — Proposed Hierarchy

**Current**: most parts flat under root. Tail chain (tail1→…→tail8) already nested. Snake tongues already deeply nested under head.

```
gorgon (0, 24, 0)
│
├── bodybottom                              ← NEW pivot (where torso meets tail)
│   ├── bodymiddle (was "bodymid")
│   │   ├── bodymidbutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       │       ├── hair, rightear, leftear      (already under head ✓)
│   │       │       ├── snake1 → snake1tongue        (already under head ✓)
│   │       │       ├── snake2 → snake2tongue        (already under head ✓)
│   │       │       ├── snake3 → snake3tongue        (already under head ✓)
│   │       │       ├── rightsnake1 → rightsnake2 → rightsnaketongue  (already ✓)
│   │       │       ├── leftsnake1 → leftsnake2 → leftsnaketongue    (already ✓)
│   │       │       └── rightbang, leftbang          (already under head ✓)
│   │       ├── rightarm                    ← MOVE from root → bodytop
│   │       ├── leftarm                     ← MOVE from root → bodytop
│   │       └── rightchest, leftchest       ← MOVE from root → bodytop
│   ├── waist1, waist2                      ← MOVE from root → bodybottom
│   └── tail1 → tail2 → … → tail8          ← MOVE from root → bodybottom (chain already nested ✓)
│
└── (no legs — snake body)
```

### Gorgon-specific notes

- `root.y = 26.0F` in `setupAnim()` adjusts for taller Gorgon model. This may need
  recalculation after re-parenting.
- The tail chain is already properly nested. Only its attachment point moves
  (from root → bodybottom).
- The undulation cycle on tail5–tail8 should still work since those rotations are
  set on the individual parts.

---

## 4 · KikimoraModel — Proposed Hierarchy

**Current**: most parts flat under root. Arm children (pauldron, armlower→cufflink), ear, skirt02/03, tail chain already nested.

```
kikimora (0, 24, 0)
│
├── bodybottom                              ← NEW pivot
│   ├── bodymiddle
│   │   ├── bodymiddlebutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       │       ├── hat                          (already under head ✓)
│   │       │       ├── rightear, leftear            (already under head ✓)
│   │       ├── rightarm                    (stays, children already nested ✓)
│   │       │   ├── rightpauldron → rightpauldronoverlay
│   │       │   └── rightarmlower → rightcufflink
│   │       ├── leftarm                     (stays, children already nested ✓)
│   │       │   ├── leftpauldron → leftpauldronoverlay
│   │       │   └── leftarmlower → leftcufflink
│   │       ├── rightchest, leftchest       ← MOVE from root → bodytop
│   │       └── skirtribbon                 ← MOVE from root → bodytop
│   ├── rightskirt01, leftskirt01           ← MOVE from root → bodybottom
│   └── tail01 → tail02 → … → tail05       ← MOVE from root → bodybottom (chain already ✓)
│
├── rightleg                                (stays under root ✓)
│   └── rightskirt02 → rightskirt03         (already under rightleg ✓)
└── leftleg                                 (stays under root ✓)
    └── leftskirt02 → leftskirt03           (already under leftleg ✓)
```

### Kikimora-specific notes

- Arms already have proper child hierarchies (pauldron, armlower). Only their
  attachment point changes (root → bodytop).
- The ear wiggle animation uses its own `zRot` on top of the default angle. Since
  ears are already children of `head`, this keeps working.
- Tail chain is already nested. Just moves from root → bodybottom.

---

## 5 · SelkieModel — Proposed Hierarchy

**Current**: most parts flat under root. Fin chain already nested under waist.

```
selkie (0, 24, 0)
│
├── bodybottom                              ← NEW pivot
│   ├── bodymiddle
│   │   ├── bodymiddlebutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       │       ├── hat1, hat2                   (already under head ✓)
│   │       ├── rightarm                    ← MOVE from root → bodytop
│   │       ├── leftarm                     ← MOVE from root → bodytop
│   │       ├── rightchest, leftchest       ← MOVE from root → bodytop
│   │       ├── chestpiece                  ← MOVE from root → bodytop
│   │       ├── hair1                       ← MOVE from root → neck or bodytop
│   │       │   └── hair2                   ← CHAIN (was flat sibling)
│   │       ├── righthatear                 ← MOVE from root → head or neck
│   │       └── lefthatear                  ← MOVE from root → head or neck
│   └── waist                               ← MOVE from root → bodybottom (children already ✓)
│       ├── zip
│       ├── fin1
│       ├── fin2
│       ├── fin3
│       ├── fin4
│       └── fintail
│
└── (no legs — mermaid tail)
```

### Selkie-specific notes

- `hair1` and `hair2` are currently flat siblings that both sync yRot to head.
  If chained as `hair1 → hair2` under `neck`, the first inherits head rotation
  and the second cascades from the first. The `hair2.yRot = head.yRot * 0.75F`
  line would need to change to a small delta instead.
- `righthatear` and `lefthatear` sync yRot to head. Making them children of `head`
  eliminates this sync.
- The undulation cycle on waist/fins should still work — those parts keep their
  relative hierarchy.

---

## 6 · VampireModel — Proposed Hierarchy

**Current**: most parts flat under root. Cloak chain (cloak1→…→cloak4) and waist chain (waist2→waist3→waist4) already nested.

```
vampire (0, 24, 0)
│
├── bodybottom                              ← NEW pivot
│   ├── bodymiddle
│   │   ├── bodymiddlebutton
│   │   └── bodytop
│   │       ├── neck
│   │       │   └── head
│   │       │       ├── headeyes            ← MOVE from root → head
│   │       │       ├── headaccessory       ← MOVE from root → head
│   │       ├── rightarm                    ← MOVE from root → bodytop
│   │       │   └── rightshoulder                    (already under rightarm ✓)
│   │       ├── leftarm                     ← MOVE from root → bodytop
│   │       │   └── leftshoulder                     (already under leftarm ✓)
│   │       ├── rightchest, leftchest       ← MOVE from root → bodytop
│   │       ├── mantle                      ← MOVE from root → bodytop
│   │       └── cloak1 → cloak2 → cloak3 → cloak4   ← MOVE root → bodytop (chain already ✓)
│   ├── waist                               ← MOVE from root → bodybottom
│   ├── waist1                              ← MOVE from root → bodybottom
│   └── waist2 → waist3 → waist4           ← MOVE from root → bodybottom (chain already ✓)
│
├── rightleg                                (stays under root ✓)
└── leftleg                                 (stays under root ✓)
```

### Vampire-specific notes

- `root.y = 6.0F + Mth.cos(…) * 0.5F` sets the bobbing animation. The base value
  (6.0F instead of 24.0F) accounts for the Vampire floating above ground. After
  re-parenting, this value may need adjustment.
- Head offset is `(0, -8, 0)` instead of `(0, -23, 0)` because the Vampire is
  shorter / offset differently. All part offsets use -8 baseline.
- Cloak chain and waist chain are already properly nested. Only their attachment
  points change.

---

## Refactoring Workflow (per model)

Follow these steps **one model at a time**. Build and test in-game after each model.

### Step 1 — Restructure `createBodyLayer()`

**1a. Add the body chain: `bodybottom → bodymiddle → bodytop → neck`**

Determine absolute Y positions of these four pivot points in the current model.
Convert to relative offsets:

```
bodybottom_offset = bodybottom_absolute - root_offset
bodymiddle_offset = bodymiddle_absolute - bodybottom_absolute
bodytop_offset    = bodytop_absolute    - bodymiddle_absolute
neck_offset       = neck_absolute       - bodytop_absolute
```

Reference values from SuccubusModel:
```java
bodybottom: (0, -13.5, -0.5)  relative to root
bodymiddle: (0,  -1.5,  1.0)  relative to bodybottom
bodytop:    (0,  -2.0,  0.4)  relative to bodymiddle
neck:       (0,  -6.0, -0.9)  relative to bodytop
head:       (0,   0.0,  0.5)  relative to neck
```

For models with offset baseline `-23`, approximate GOG-style values:
```java
bodybottom: (0, -13.5,  0)
bodymiddle: (0,  -1.5,  0.5)
bodytop:    (0,  -2.0,  0)
neck:       (0,  -6.0, -1.0)
head:       (0,   0.0,  0)
```

**1b. Re-parent head-related parts under `head`**

Move `headeyes` and `headaccessory` from `root.addOrReplaceChild(…)` to
`head.addOrReplaceChild(…)`. Their offset becomes `(0, 0, 0)` (or a tiny Z offset
for the eyes overlay) instead of the absolute position they had before.

```java
// Before:
root.addOrReplaceChild("headeyes", …, PartPose.offset(0, -23, 0));

// After:
head.addOrReplaceChild("headeyes", …, PartPose.offset(0, 0, -0.1F));
```

**1c. Re-parent arms under `bodytop`**

```java
// Before:
root.addOrReplaceChild("rightarm", …, PartPose.offset(-2.5F, -21.5F, 0));

// After (offset relative to bodytop):
bodytop.addOrReplaceChild("rightarm", …, PartPose.offset(-2.5F, -4.5F, -0.9F));
```

The Y offset of the arm relative to bodytop = arm_absolute_Y − bodytop_absolute_Y.
Z offset may also need adjustment.

**1d. Re-parent decorative parts**

Move body-level decorations (chest pieces, mantle, cloaks, waist, etc.) to their
anatomically correct parents. Recalculate offsets accordingly.

**1e. Keep legs under root**

Legs stay as direct children of root (the GOG pattern). Their offsets stay the same.

**1f. Redistribute mesh boxes**

The current `bodybottom`, `bodymiddle`, `bodytop` entries under root only carry
boxes (no children). In the new structure, the box definitions merge into the new
PartDefinition nodes at the correct positions. Adjust `addBox()` coordinates to
account for the new offset origin.

### Step 2 — Update Constructor

Change all `getChild()` lookups to reflect the new nesting:

```java
// Before:
this.root = root.getChild("modelname");
this.head = this.root.getChild("head");
this.headeyes = this.root.getChild("headeyes");
this.bodytop = this.root.getChild("bodytop");
this.rightarm = this.root.getChild("rightarm");

// After:
this.root = root.getChild("modelname");
ModelPart bodybottom = this.root.getChild("bodybottom");
this.bodytop = bodybottom.getChild("bodymiddle").getChild("bodytop");
ModelPart neck = this.bodytop.getChild("neck");
this.head = neck.getChild("head");
this.headeyes = this.head.getChild("headeyes");
this.rightarm = this.bodytop.getChild("rightarm");
```

### Step 3 — Simplify `setupAnim()`

**Remove manual rotation sync** for parts that are now children:

```java
// DELETE these lines (headeyes/headaccessory now inherit from head):
headeyes.yRot = head.yRot;
headeyes.xRot = head.xRot;
headaccessory.yRot = head.yRot;
headaccessory.xRot = head.xRot;
```

For parts like `hair` or `mantle` that manually synced yRot to head, check if
they are now children of `head` or `neck` — if so, remove the sync line.

**Cloak/cape chains**: If previously flat siblings with independent rotations,
now they cascade. Adjust magnitudes:

```java
// Before (flat, each gets own absolute rotation):
cloak1.zRot = Mth.cos(…) * 0.1F;
cloak2.zRot = Mth.cos(…) * 0.1F;
cloak3.zRot = Mth.cos(…) * 0.1F;

// After (chained, rotations accumulate):
cloak1.zRot = Mth.cos(…) * 0.1F;
cloak2.zRot = Mth.cos(…) * 0.05F;  // smaller since it adds to parent
cloak3.zRot = Mth.cos(…) * 0.03F;  // even smaller
```

(Exact values need in-game tuning.)

### Step 4 — Update `translateToHand()`

With arms nested under `bodytop`, the transform chain is automatically walked.
Follow the GOG pattern:

```java
@Override
public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
    // Small translation offset — tune in-game
    poseStack.translate(0, 0.5, 0);
    getArm(arm).translateAndRotate(poseStack);
}
```

The `poseStack.translate()` values differ per model. Start with the SuccubusModel
values `(-0.0625, 0.5, 0)` and adjust.

> **Important**: With the old flat hierarchy, a workaround was to call
> `root.translateAndRotate(poseStack)` before the arm. With the new nested
> hierarchy this workaround is no longer needed and must be removed.

### Step 5 — Adjust `root.y` Overrides

Some models override `root.y` in `setupAnim()`:

| Model | Current `root.y` | Notes |
|-------|-------------------|-------|
| Gorgon | `26.0F` (static) | May need recalculation after bodybottom pivot added |
| Vampire | `6.0F + cos(…)` (bobbing) | Base value accounts for floating height |
| Others | not set (uses PartPose default 24) | Should be fine after refactor |

After re-parenting, `root.y` affects the entire model. If the visual position
shifts, adjust this value until the model stands/floats at the correct height.

### Step 6 — In-Game Testing Checklist

For each refactored model, verify:

- [ ] Model appears at correct position (not floating or sunken)
- [ ] Head rotation (yaw + pitch) looks correct, no doubled rotation
- [ ] `headeyes` blink overlay aligns with head
- [ ] `headaccessory` hat overlay aligns with head
- [ ] Arms swing correctly during walking
- [ ] Idle arm sine/cosine animation works
- [ ] Attack animation (holdingMelee) works
- [ ] Hand-held items positioned correctly (translateToHand)
- [ ] Body decorations (mantle, cloak, cape, skirt) animate correctly
- [ ] Tail / fin undulation cycle works (Gorgon, Selkie, Kikimora)
- [ ] Riding pose works (models that support it)
- [ ] Chest pieces positioned correctly
- [ ] No z-fighting or misaligned overlays

---

## Summary Table

| Model | Key Changes | Chains to Fix |
|-------|-------------|---------------|
| **Baphomet** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move cloak/mantle → body | cloak1/2/3: flat → chain |
| **Dhampir** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move cape/mantle/hair → body | cape1/2: flat → chain |
| **Gorgon** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move tail → bodybottom | *(tail already chained)* |
| **Kikimora** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move tail/skirts → bodybottom | *(tail already chained)* |
| **Selkie** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move waist+fins → bodybottom | hair1/2: flat → chain |
| **Vampire** | Add body chain; move headeyes/headaccessory → head; move arms → bodytop; move cloak/waist → body | *(cloak & waist already chained)* |

---

## Recommended Order

Refactor in this order (simplest first):

1. **Gorgon** — no legs, tail chain already nested, snake hierarchy already deeply nested under head
2. **Selkie** — no legs, fin hierarchy already under waist
3. **Vampire** — cloak and waist chains already nested, only 2 legs (no complex leg hierarchy)
4. **Baphomet** — straightforward humanoid, head children already nested
5. **Dhampir** — similar to Baphomet, has cape chain to fix
6. **Kikimora** — most complex: arms have deep sub-hierarchy, skirts under legs, tail chain
