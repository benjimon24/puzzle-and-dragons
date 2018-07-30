# Puzzle and Dragons

This is a clone of the popular mobile game, Puzzle and Dragons, built on Java 8 using the Processing . It is a match-3 type puzzle game like Bejeweled or Candy Crush, except you can drag a piece as far as you want and swap with every piece you pass through along the way until you let go.


----
## Technologies used:
1. Java 8
2. Processing 3.3.6

----
## Process / Approach
I never had any experience using any graphics containers so building this game was a pretty big undertaking. I was not sure how things would look like in the end but I spent the first day just messing around with Processing and trying to get things to show up.

The first thing I tried to do was create a board with a set of orbs of different colors. In the beginning I was not sure what attributes the orbs needed to store, but I just added to them as I went along.

Next, I tried to figure out how to interact with the orbs on my board. I had to create functions for picking up the orbs and dragging them across the board. After that, I had to write logic so that every time I passed over an orb, it would swap positions with the orb I am currently dragging.

After that, I created the logic to detect matches and get rid of them. After that I created the logic to make orbs cascade to the bottom. Finally, I had to figure out how to create animations for these game functions so that everything does not happen instantaneously, otherwise it would be very confusing to the user.

----
## Unsolved problems
* There was a moment when the app got stuck and froze my computer. I got a very lucky cascade in-game so my excitement was quite short-lived. At this point I am not quite sure how it happened.
* Users can cheat by dragging an orb outside the board and cutting to the other side.
* Users can interact with orbs while they are still moving into position.
* I would like orbs to fade out as they get deleted.
* I would like to track the number of combos in a single move
* I still need to add a proper game start / history interface.

___
## Biggest Wins/Challenges
* One of the biggest wins was definitely creating the logic to drag and drop orbs. The moment I got this working I knew the rest would be possible.
* The other big challenge was realizing that my code was processing extremely quickly, and that I would need to animate effects such as cascading slowly in order for the user to understand what is happening.

----
## Thanks:
* [Processing](https://processing.org/)

