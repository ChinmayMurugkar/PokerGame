This can be run by following command :

./gradlew clean build bootRun            

The game has been divided into model view controller.
Following tech is used :
Springboot - I have not autowired any objects here
Junit - testing framework
Java 8
slj4 Logger

game state models have been made singleton to keep track of scores and player through all the games. 
Once player created they will play 50 games. Dealer deals the cards and puts them back in the deck
Interesting factor is the behavior of the outcome on shuffling every time and shuffling at every 10th game.
I have created histograms in the end to show the outcomes of the players and the game.
