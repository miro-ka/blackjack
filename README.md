# blackjack

<img src="https://user-images.githubusercontent.com/1301154/30350704-30608d48-9818-11e7-8cce-d3bead0f542f.png" width="256">


## About 
Blackjack game


## Requirements 
 - Java 8+
 - Maven


## Quick Start
Core Library BlackJack should be easy to adapt to any client. As an example you can take a look at console BlackJack implementation.

Start console BlackJack client:

```
java -jar blackjack_console.jar
```

### Help and input arguments


```
__________.__                 __          ____.              __  
\______   \  | _____    ____ |  | __     |    |____    ____ |  | __
 |    |  _/  | \__  \ _/ ___\|  |/ /     |    \__  \ _/ ___\|  |/ /
 |    |   \  |__/ __ \  \___ |    <  /\__|    |/ __ \  \___|    < 
 |______  /____(____  /\___  >__|_ \ \________(____  /\___  >__|_ \
        \/          \/     \/     \/               \/     \/     \/ 


usage: Black Jack
 -d,--deck <arg>   csv file containing deck of cards
 -h,--help         shows help

```


## Rules 

 - two players (called Sam and the Mr. Dealer) play against each other
 - each player is given two cards from the top of a shuffled deck of cards
 - check if either player has blackjack (21) with their initial hand and wins the game
 - if neither player has blackjack then Sam can start drawing cards from the top of
the deck
 - Sam should stop drawing cards from the deck if their total reaches 17 or higher
 - Sam has lost the game if their total is higher than 21
 - when Sam has stopped drawing cards the Dealer can start drawing cards from
the top of the deck
 - the Dealer should stop drawing cards when their total is higher than Sam.
 - the Dealer has lost the game if their total is higher than 21
 
 
 
 
## Bugs & Improvements
Just send me pull request and I will try to check it ASAP.
