# My Game
Simple Java 2D Game

```bash
# created maven project
mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DgroupId=com.github.saviosailas \
  -DartifactId="MyGame" \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.gitlab.saviosailas.my_game \
  -DinteractiveMode=false

# mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5
```

### Day 1

![Day 01 status screenshot](screenshot/day01.png)

### Day 2
- Added key listener for W, A, S ans D
- Added 60 FPS animation
![Day 02 status screenshot](screenshot/day02.gif)


Let’s break down this Java function in a way that’s easy to understand for a 15-year-old who’s new to coding. This code is likely part of a game loop, which is the “heartbeat” of a video game. It controls how often the game updates and redraws the screen to make things move smoothly. I’ll explain each line, the math behind it, why it’s written this way, and include simple examples to make it clear.

---
```java
@Override
    public void run() {
        double drawIntervel = (oneBillion / fps);
        long lastTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta = delta + (currentTime - lastTime) / drawIntervel;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
```

### **What is this function doing?**

This function is called `run()` and is part of a class that implements the `Runnable` interface (common in Java for running tasks in a separate thread). The `@Override` means it’s replacing a default version of the `run()` method from the `Runnable` interface.

The purpose of this code is to:
1. Run a game loop that keeps the game alive.
2. Control how often the game updates its logic (like moving a character) and redraws the screen (like showing the character’s new position).
3. Try to maintain a consistent speed, measured in **frames per second (FPS)**, so the game looks smooth.

---

### **Line-by-line explanation**

Let’s go through each line and explain what it does, including the math and why it’s needed.

#### **1. `@Override`**
```java
@Override
```
- **What it means**: This tells Java that this `run()` method is replacing a method from a parent class or interface (in this case, `Runnable`).
- **Why it’s here**: The `Runnable` interface requires a `run()` method to define what happens when a thread starts. By overriding it, we’re saying, “Here’s my custom version of what to do when the thread runs.”
- **For a 15-year-old**: Think of it like rewriting the instructions for a toy robot. The robot comes with a default “walk forward” command, but you’re saying, “No, I want it to dance instead.”

---

#### **2. `public void run()`**
```java
public void run() {
```
- **What it means**: This defines the `run()` method, which is the main code that runs when the game’s thread starts. `public` means anyone can call it, `void` means it doesn’t return anything.
- **Why it’s here**: This is where the game loop lives. It’s like the engine of a car that keeps running to make the game work.
- **For a 15-year-old**: Imagine this as the “start button” for your game. When you press it, this code starts and keeps the game moving.

---

#### **3. `double drawIntervel = (oneBillion / fps);`**
```java
double drawIntervel = (oneBillion / fps);
```
- **What it means**:
  - `oneBillion` is likely a constant equal to 1,000,000,000 (1 billion nanoseconds, which is 1 second).
  - `fps` is the desired **frames per second** (e.g., 60 FPS means 60 updates per second).
  - `drawIntervel` calculates how many **nanoseconds** should pass between each frame to achieve the desired FPS.
  - `double` means it’s a number that can have decimal points (like 16.6667).
- **Math calculation**:
  - If `fps = 60`, then:
    \[
    \text{drawIntervel} = \frac{1,000,000,000}{60} \approx 16,666,666.6667 \text{ nanoseconds}
    \]
  - This means each frame should take about 16.67 million nanoseconds (or roughly 16.67 milliseconds, since 1 millisecond = 1,000,000 nanoseconds).
- **Why it’s here**: The game needs to know how long to wait between updates to keep a consistent speed. If you want 60 FPS, each frame gets \( \frac{1}{60} \) seconds.
- **For a 15-year-old**: Imagine you’re flipping through a flipbook to make a cartoon move. To make it look smooth at 60 pages per second, you need to flip a new page every \( \frac{1}{60} \) seconds. This line calculates that time in nanoseconds.
- **Why nanoseconds?**: Computers are super fast, so we use nanoseconds (billionths of a second) for precise timing.

---

#### **4. `long lastTime = System.nanoTime();`**
```java
long lastTime = System.nanoTime();
```
- **What it means**:
  - `System.nanoTime()` gets the current time in nanoseconds (a very precise clock).
  - `lastTime` stores this time as a `long` (a large whole number).
- **Why it’s here**: We need to know when the last frame happened so we can calculate how much time has passed since then.
- **For a 15-year-old**: Think of this as checking your watch when you start running a lap. You’ll check it again later to see how long the lap took.

---

#### **5. `long currentTime;`**
```java
long currentTime;
```
- **What it means**: Declares a variable `currentTime` to store the current time later in the loop.
- **Why it’s here**: We’ll use this to keep track of the time each time the loop runs.
- **For a 15-year-old**: This is like reserving a spot to write down the time when you finish your next lap.

---

#### **6. `double delta = 0;`**
```java
double delta = 0;
```
- **What it means**: `delta` is a variable that keeps track of how much time has accumulated toward the next frame. It starts at 0.
- **Why it’s here**: We use `delta` to decide when enough time has passed to update and draw the next frame.
- **For a 15-year-old**: Imagine you’re saving up pennies to buy a $1 candy. `delta` is how many pennies you’ve saved so far. When you get to 100 pennies (1 dollar), you can “buy” the next frame.

---

#### **7. `while (gameThread != null) {`**
```java
while (gameThread != null) {
```
- **What it means**: This starts a loop that keeps running as long as `gameThread` is not null. `gameThread` is likely the thread running the game.
- **Why it’s here**: This is the game loop. It keeps the game running until the thread is stopped (e.g., when you close the game).
- **For a 15-year-old**: This is like saying, “Keep playing the game until I tell you to stop.” The game stops when `gameThread` is set to null (like pressing the “quit” button).

---

#### **8. `currentTime = System.nanoTime();`**
```java
currentTime = System.nanoTime();
```
- **What it means**: Updates `currentTime` with the current time in nanoseconds.
- **Why it’s here**: We need to know the current time to calculate how much time has passed since the last frame.
- **For a 15-year-old**: This is like checking your watch again at the end of your lap to see how long it took.

---

#### **9. `delta = delta + (currentTime - lastTime) / drawIntervel;`**
```java
delta = delta + (currentTime - lastTime) / drawIntervel;
```
- **What it means**:
  - `currentTime - lastTime` calculates how many nanoseconds have passed since the last frame.
  - Dividing by `drawIntervel` converts that time into a fraction of a frame.
  - Add this fraction to `delta` to keep track of accumulated time.
- **Math calculation**:
  - Suppose `fps = 60`, so `drawIntervel ≈ 16,666,666.6667` nanoseconds.
  - If `currentTime - lastTime = 8,333,333` nanoseconds (half a frame’s time):
    \[
    \frac{8,333,333}{16,666,666.6667} \approx 0.5
    \]
    So, `delta = delta + 0.5`.
  - If `delta` was 0.3 before, now `delta = 0.3 + 0.5 = 0.8`.
- **Why it’s here**: This tracks how close we are to needing a new frame. When `delta` reaches 1 or more, it means enough time has passed for a full frame.
- **For a 15-year-old**: Remember the candy example? If each frame costs $1, and you earn 50 cents in one loop, you add 0.5 to `delta`. When `delta` gets to 1 or more, you’ve saved enough to “buy” a frame.
- **Why divide by `drawIntervel`?**: Dividing converts raw time (nanoseconds) into a fraction of a frame, making it easier to check when a full frame’s time has passed.

---

#### **10. `lastTime = currentTime;`**
```java
lastTime = currentTime;
```
- **What it means**: Updates `lastTime` to the current time, so the next loop can measure time from this point.
- **Why it’s here**: We need to reset the starting point for the next time calculation.
- **For a 15-year-old**: After finishing a lap, you note the end time as the start time for your next lap.

---

#### **11. `if (delta >= 1) {`**
```java
if (delta >= 1) {
```
- **What it means**: Checks if `delta` is at least 1, meaning enough time has passed for at least one frame.
- **Why it’s here**: We only want to update and draw the game when enough time has passed to keep the FPS consistent.
- **For a 15-year-old**: This is like checking if you’ve saved enough pennies (100) to buy the $1 candy. If yes, you can “spend” it on a frame.

---

#### **12. `update();`**
```java
update();
```
- **What it means**: Calls a method named `update()`, which likely handles the game’s logic (e.g., moving characters, checking collisions).
- **Why it’s here**: This is where the game figures out what’s happening next (like making your character jump).
- **For a 15-year-old**: Think of this as updating your game’s story, like deciding where Mario moves next.

---

#### **13. `repaint();`**
```java
repaint();
```
- **What it means**: Calls a method named `repaint()`, which tells the game to redraw the screen (e.g., show Mario in his new position).
- **Why it’s here**: After updating the game’s logic, we need to show the new state on the screen.
- **For a 15-year-old**: This is like drawing the next page in your flipbook to show the updated cartoon.

---

#### **14. `delta--;`**
```java
delta--;
```
- **What it means**: Subtracts 1 from `delta`, since we just “used up” one frame’s worth of time.
- **Why it’s here**: We need to reset `delta` to track the next frame. If `delta` was 1.2, it becomes 0.2, keeping the extra 0.2 for the next loop.
- **For a 15-year-old**: After buying the $1 candy, you subtract $1 from your savings. If you had $1.20, you’re left with $0.20 to keep saving.
- **Why not `delta = 0`?**: Sometimes the loop runs a bit late, so `delta` might be more than 1 (e.g., 1.2). Keeping the extra fraction ensures we don’t lose that time, making the game smoother.

---

#### **15. Closing braces `}`**
```java
        }
    }
```
- These close the `if` block, the `while` loop, and the `run()` method.

---

### **Why is the code written this way?**

This code uses a **fixed timestep game loop** with **delta time** to keep the game running at a consistent speed (e.g., 60 FPS) no matter how fast or slow the computer is. Here’s why each part is important:

1. **Using nanoseconds**: Computers are fast, and games need precise timing. Nanoseconds allow us to measure tiny time differences accurately.
2. **Delta time**: By accumulating time in `delta`, the game can handle situations where the computer runs faster or slower. If the computer is slow and a frame takes longer, `delta` might be 1.5, so the game can “catch up” by updating and drawing once but keeping the extra 0.5.
3. **Fixed FPS**: Dividing by `drawIntervel` ensures the game updates exactly 60 times per second (or whatever `fps` is), making movement smooth and predictable.
4. **Thread loop**: The `while (gameThread != null)` keeps the game running until the player quits, and using a separate thread prevents the game from freezing the rest of the program.
5. **Separate `update()` and `repaint()`**: Splitting logic (what happens) and rendering (what’s drawn) makes the code easier to manage and reuse.

---

### **Example: How it works step-by-step**

Let’s pretend `fps = 60`, so `drawIntervel = 16,666,666.6667` nanoseconds. Here’s how the loop runs over a few iterations:

1. **First loop**:
   - `lastTime = 1,000,000,000` (some starting time).
   - `currentTime = 1,008,333,333` (8.33 million nanoseconds later).
   - Time passed = `1,008,333,333 - 1,000,000,000 = 8,333,333`.
   - `delta = 0 + 8,333,333 / 16,666,666.6667 ≈ 0.5`.
   - `lastTime = 1,008,333,333`.
   - `delta < 1`, so skip `update()` and `repaint()`.

2. **Second loop**:
   - `currentTime = 1,016,666,666` (another 8.33 million nanoseconds).
   - Time passed = `1,016,666,666 - 1,008,333,333 = 8,333,333`.
   - `delta = 0.5 + 8,333,333 / 16,666,666.6667 ≈ 0.5 + 0.5 = 1.0`.
   - `lastTime = 1,016,666,666`.
   - `delta >= 1`, so:
     - Call `update()` (move game objects).
     - Call `repaint()` (draw the screen).
     - `delta = 1.0 - 1 = 0.0`.

3. **Third loop**:
   - Repeat, accumulating time until `delta >= 1` again.

This keeps the game updating exactly 60 times per second, even if the computer’s speed varies slightly.

---

### **What happens if we don’t do it this way?**

- **No delta time**: If we just updated every loop, a fast computer might update 1000 times per second, making the game too fast, while a slow computer might update 10 times per second, making it sluggish.
- **No fixed FPS**: Without `drawIntervel`, the game’s speed would depend on the computer’s speed, making it inconsistent.
- **No thread**: Without a separate thread, the game might freeze the window’s controls (like the “close” button).

---

### **For a 15-year-old: A fun analogy**

Imagine you’re making a stop-motion movie with a toy car. You want it to move smoothly at 60 frames per second:
- Each frame is a photo you take.
- You need to take a photo every \( \frac{1}{60} \) seconds (like `drawIntervel`).
- You check your watch (`System.nanoTime()`) to see how much time has passed.
- You keep a tally (`delta`) of how close you are to needing the next photo.
- When you’ve waited long enough (`delta >= 1`), you move the car (`update()`) and take a photo (`repaint()`).
- You subtract the time you used (`delta--`) and keep going until you stop filming (`gameThread == null`).

This code is like a super-precise director making sure every photo is taken at the right time to make your movie look awesome!

---

### **Tips for learning more**

- **Try it out**: If you’re coding a game, print `delta` or `currentTime - lastTime` to see how the timing works.
- **Change FPS**: Set `fps` to 30 or 120 and see how the game feels.
- **Read about game loops**: Search for “Java game loop tutorial” to see other ways to do this.
- **Ask questions**: If you’re stuck on `update()` or `repaint()`, let me know, and I can explain those too!

---

Let me know if you want me to explain anything further or show you how to test this code!
