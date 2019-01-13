/* CSCI 1100 - Team Project - Part 2
This program plays our team's kindergarten math learning game modules.
Team Member: Chloe Bae (B00764526), Michelle Suk (B00720469)
Date: 20 Nov, 2017 */

//Import the java util api's.
import java.util.Scanner;
import java.util.Random;

//This class plays our team's kindergarten math learning game modules.
public class TeamProjectFinal{

   //global game control variables
   static Scanner sc = new Scanner(System.in);
   static int numOfLevel=5, numOfUnitPerLevel=5, numOfChallenge=5, kindergartenGradeMax, lvlPassPercent;
   static int allLvlSuccess, allLvlFail, allLvlCount;
   static int successCount, failCount, totalCount;
   static int gameLevel, displayLevel, goToLevel, nextChQIndex, gameMoney;
   static String programName, userNickName, userGrade;
   static boolean isChQMaxReached, isGameOver, stayAtZeroLevel, isNewLevelStart;
   static double userScorePercent;
   
   //visual separator
   static String sectionBar    ="********************************************************";
   static String gameBar       ="########################################################";
   static String splitBar      ="--------------------------------------------------------";
   
   //welcome message
   static String introMsg            ="Welcome to the game. My name is %s.";
   static String userNickNameMsg     ="What is your nickname?";
   static String userNameAgainMsg    ="Enter your nickname.";
   
   //user info message
   static String userGradeMsg        ="Hi, %s. Nice to meet you. What is your grade (Answer 1, 2, 3, or 4)?";
   static String userGradeAgainMsg   ="Choose 1, 2, 3, or 4?";
   
   //user start-level message
   static String startLevelMsg       ="Your grade is %s. \n\nDo you want to start game at the same grade "+
                                      "level? \n(Enter y for yes, n for no)";                                  
   static String sLevelYNMsg         ="Please enter y or n.";
   static String startLevelInputMsg  ="Enter a level you want to play (Enter 1, 2, 3, 4, or 5):";
   static String startLevelNotIntMsg ="It's not a number. Choose 1, 2, 3, 4, or 5?";
   static String startLevelAgainMsg  ="There is no such level. Choose 1, 2, 3, 4, or 5?";
   static String levelConfirmMsg     ="Alright. You chose level %s ! Good luck !";
   
   //level-start message
   static String gameIntroMsg     ="<<< Welcome to level %s ! >>>";
   static String gameRuleMsg      ="Each time you get a right answer, you will earn $%s.";
   static String userMoneyMsg     ="Your TOTAL GAME MONEY (from all levels) is $%s.";   
      
   //after-each-question message
   static String rightAnsMsg      ="CORRECT! Congratulations. You've earned $%s!";
   static String wrongAnsMsg      ="NOT CORRECT. Will you try again (Enter y for yes, n for no) ?";
   static String currentScoreMsg  ="Your FINAL SCORE (at this level): %.0f / 100.";
   
   //analysis message
   static String lvlAnaysisMsg    ="Your RESULT (at this level): Out of %s total attempts, "+
                                   "\nyou got %s CORRECT, %s INCORRECT.";
   static String gameAnaysisMsg   ="Your GAME SUCCESS RATE (from all levels): "+
                                   "\n%s TOTAL ATTEMPTS: %s CORRECT, %s INCORRECT."; 
   
   //level/game-done message
   static String levelOverMsg     ="************ LEVEL %s IS OVER ***************************";
   static String congratsMsg      ="***** RESULT: CONGRATULATIONS ! ************************";
   static String encourageMsg     ="*** RESULT: YOU NEED TO PRACTICE MORE ! ****************";
   static String levelUpMsg       ="** LET'S MOVE TO A HIGHER LEVEL - LEVEL %s **************";
   static String levelDownMsg     ="** YOU WILL MOVE DOWN TO LEVEL %s TO PRACTICE MORE. ****";
   static String zeroLevelMsg     ="** YOU WILL PLAY LEVEL %s AGAIN (THE EASIEST LEVEL). ****";
   static String gameOverMsg      ="** GAME's OVER! YOU'VE SUCCEESSFULLY FINISHED THE HIGHEST LEVEL! **";
   
   //challenge question message
   static String chHeaderMsg      = "{{/////////////////////// CHALLENGE QUESTION /////////////////////////}}";
   static String chRuleMsg        = "{{//////// You will DOUBLE YOUR MONEY if you get this right! /////////}}";
   static String chCongratsMsg    = "CONGRATS! YOU DOUBLED YOUR GAME MONEY !!";
   static String chFailMsg        = "SORRY - WRONG ANSWER. GOOD LUCK NEXT TIME!";
   static String chEndMsg         = "{{/////////////////////// CHALLENGE FINISHED /////////////////////////}}";
   static String chTailMsg        = "{{////////////////////////////////////////////////////////////////////}}";
   static String numGuideMsg      = " \n-> Which number should go into the blank?";
                        
   //exit message
   static String exitGameBar      ="************ GAME FINISHED *****************************";
   static String exitGameMsg      ="Do you wish to continue playing? (y for yes, n for no)";
   static String goodByeMsg       ="It was nice playing with you! See you again %s!!";

   //Level 1-5 question & answer variables
   static String[][] questions = new String[numOfLevel][numOfUnitPerLevel];
   static String[][] answers = new String[numOfLevel][numOfUnitPerLevel];
   
   //Challenge questions & answers variables
   static String[] chQuestions = new String[numOfChallenge];
   static String[] chAnswers = new String[numOfChallenge];  
   
   //Money token variables.
   static int[] moneyPraiseByLevel = new int[numOfLevel];

   //This method performs the main operation of this class.
   public static void main(String[] args){
   
         //Main game stream.
         initGame();
         initQA();
         intro();         
         playLevel();
   }
   
   //******** main game stream - begin ***********//
   
   //This method initializes the game control variables.
   public static void initGame(){
      programName="Smiley";
      
      kindergartenGradeMax=4;
      lvlPassPercent=50; //percentage mininum required to pass to the next level.
      
      isChQMaxReached=false; 
      isGameOver=false;
      stayAtZeroLevel=false;
      isNewLevelStart=false;
      
      //Initialize variables to decide the amount of money earned for each question at each level.
      moneyPraiseByLevel[0]=1; //1 dollar is earned when successful at each of level 1 questions.
      moneyPraiseByLevel[1]=3; //3 dollar is earned when successful at each of level 2 questions.
      moneyPraiseByLevel[2]=5; //5 dollar is earned when successful at each of level 3 questions.
      moneyPraiseByLevel[3]=7; //7 dollar is earned when successful at each of level 4 questions.
      moneyPraiseByLevel[4]=9; //9 dollar is earned when successful at each of level 5 questions.
   }
   
   //This method initializes the contents of all questions and answers.
   public static void initQA(){
   
      /*Level 1 Q&A initialization:
      //Question Topic: Addition - Numbers up to 5
      //Question Topic: Subtraction - Numbers up to 2 
      //Question Topic: Multiplication - Number up to 5
      //Question Topic: Division - Number up to 2
      */
      
      //Level 1 questions.
      questions[0][0]="1 + 2 = __";
      questions[0][1]="You have 2 basket balls and you gave 1 to your friend John. "+
                      "\nHow many basket balls do you have now?";
      questions[0][2]="2 x 1 = __";
      questions[0][3]="A store sells a candy for 1 dollar. You bought 1 candy. "+
                      "\nHow much do you have to pay? (Enter a number) $";
      questions[0][4]="You had two apples. \nYou divided them equally into 2 bags."+
                      "\nHow many apple(s) are in each bag? "+
                      "\nWhat is 2 / 2 = __";
      
      //Level 1 answers.
      answers[0][0]="3";
      answers[0][1]="1";
      answers[0][2]="2";
      answers[0][3]="1";
      answers[0][4]="1";
      
      /*Level 2 Q&A initialization:
      //Question Topic: Addition - Numbers up to 6
      //Question Topic: Subtraction - Numbers up to 4 
      //Question Topic: Multiplication - Number up to 6
      //Question Topic: Division - Number up to 4*/
      
      //Level 2 questions.
      questions[1][0]="What is 2 + 3 = __";
      questions[1][1]="You had 5 bananas and daddy came home and ate 1 banana."+
                      "\nHow many bananas do you have now?";
      questions[1][2]="You and Daddy - each person bought 3 pencils."+
                      "\nIn total, how many pencils you and your daddy have? "+
                      "\n2 x 3 = __";
      questions[1][3]="You went to a store and bought a 2 dollar pen"+
                      "\nand a 2 dollar chocolate bar. "+
                      "\nHow much do you have to pay? (Enter a number) $";
      questions[1][4]="You and Mom had 4 candies and divide them equally into 2 bags."+
                      "\nHow many candies are in each bag?"+
                      "\nWhat is 4 / 2 = __";
      
      //Level 2 answers.
      answers[1][0]="5";
      answers[1][1]="4";
      answers[1][2]="6";
      answers[1][3]="4";
      answers[1][4]="2";
      
      /*Level 3 Q&A initialization:
      //Question Topic: Addition - Numbers up to 10
      //Question Topic: Subtraction - Numbers up to 5 
      //Question Topic: Multiplication - Numbers up to 10
      //Question Topic: Division - Numbers up to 6
      */
      
      //Level 3 questions.
      questions[2][0]="3 + 3 = __";
      questions[2][1]="You had 5 sunflowers and you gave 2 to your friend Kim, "+
                      "\nand gave 2 to your uncle Mark. "+
                      "\nHow many sunflowers do you have left?";
      questions[2][2]="Your family (you, mom and dad), each person needs 3 bottles of water for camping."+
                      "\nIn total, how many bottles of water do your family need to buy?"+
                      "\nWhat is 3 x 3 = __";
      questions[2][3]="A sandwich costs 4 dollars and a bottle of soda costs 4 dollars. "+
                      "\nYou bought 1 sandwich and 1 soda. "+
                      "\nHow much do you have to pay? (Enter a number) $";
      questions[2][4]="What is 6 / 3 = __";
      
      //Level 3 answers.
      answers[2][0]="6";
      answers[2][1]="1";
      answers[2][2]="9";
      answers[2][3]="8";
      answers[2][4]="2";
      
      /*Level 4 Q&A initialization:
      //Question Topic: Addition - Numbers between 10 and 20
      //Question Topic: Subtraction - Numbers up to 7 
      //Question Topic: Multiplication - Numbers up to 20
      //Question Topic: Division - Numbers up to 7
      */
      //Level 4 questions.
      questions[3][0]="10 + 5 = __";
      questions[3][1]="There were 5 cars in the parking lot. 1 car came in, "+
                      "\nand 1 car left. How many cars are left in the parking lot?";
      questions[3][2]="You have 4 family members in your family."+
                      "\nAnd each family member needs 5 new shirts."+
                      "\nIn total, how many new shirts do your family need to buy?"+
                      "\n4 x 5 = __";
      questions[3][3]="You went to a store to buy 2 notebooks. "+
                      "\nThe store cells a notebook for 5 dollars each."+
                      "\nHow much do you have to pay?(Enter a number) $";
      questions[3][4]="What is 6 / 2 = ___";
      
      //Level 4 answers.
      answers[3][0]="15";
      answers[3][1]="5";
      answers[3][2]="20";
      answers[3][3]="10";
      answers[3][4]="3";
      
      /*Level 5 Q&A initialization:
      //Question Topic: Addition - Numbers above 20
      //Question Topic: Subtraction - Numbers up to 10 
      //Question Topic: Multiplication - Numbers above 20
      //Question Topic: Division - Numbers up to 10
      */
      //Level 5 questions.
      questions[4][0]="20 + 2 = __";
      questions[4][1]="You had 3 fishes in your fish tank and 1 of them died. "+
                      "\nSo you got 2 more fishes from a pet shop."+
                      "\nHow many fishes do you have in the tank?";
      questions[4][2]="What is 10 x 3 = __";
      questions[4][3]="You went to a bookstore, and bought 2 comic books. "+
                      "\nOne book costs 10 dollars and the other book costs 5 dollars. "+
                      "\nHow much do you have to pay?(Enter a number) $";
      questions[4][4]="You have 8 books. \nYou want to give them away to your 4 friends equally."+
                      "\nWhat is 8 / 4 = __";
      
      //Level 5 answers.
      answers[4][0]="22";
      answers[4][1]="4";
      answers[4][2]="30";
      answers[4][3]="15";
      answers[4][4]="2";
      
      //Challenge Q&A initialization:
      //challenge questions.
      chQuestions[0]="Now it's 7 o'clock in the morning. "+
                     "\nAfter 2 hours, it will be __ o'clock in the morning.";
      chQuestions[1]="Mom gave you 5 dollar bill. So you went to a toy store "+
                      "\nand bought a toy car for 1 dollar and a toy guitar for 2 dollars. "+
                      "\nYou paid a 5 dollar bill. "+
                      "\nYou will get back __ dollars.";
      chQuestions[2]="Now it's 11 o'clock in the morning. "+
                     "\nAfter 2 hours, it will be __ o'clock in the afternoon.";
      chQuestions[3]="You have 10 chocolate chip cookies. You need ___ more cookies"+
                     "\nto make it 16.";
      chQuestions[4]="You have 3 car toys. You need ___ more car toys"+
                     "\nto make it 10.";
      
      //challenge question answers.
      chAnswers[0]="9";
      chAnswers[1]="2";
      chAnswers[2]="1";
      chAnswers[3]="6";
      chAnswers[4]="7";  
   }  
   
   //This method manages the 'intro' screen.
   public static void intro(){
   
      //Print out intro bars, messages, and program face.
      tell(sectionBar);
      tellWithFormat(introMsg, programName); 
      drawProgramFace();    
      emptyLine();   
      
      //Get the user nick name.
      userNickName = askString(userNickNameMsg); 
      
      //Validate the answer. 
      while(!validateNotEmpty(userNickName)){
             userNickName = askString(userNameAgainMsg);
      }  
      emptyLine(); 
      
      //Get the user grade.
      getUserGrade();
      emptyLine();
      
      //Decide whether or not to start at the same level as user's grade.
      decideGameStartLevel();
      emptyLine();
      tellWithFormat(levelConfirmMsg, displayLevel);
      emptyDoubleLine();
      tell(sectionBar);
   }

   //This method controls each 'level'.
   public static void playLevel(){
   
      //Unless game's over, proceeds to the next level.
      while(isGameOver==false){
         
         //Initialize all level related variables.
         initializeLevel();
         
         //Display messages (intro, game rule, current monoey, game score) to the user.
         tell(gameBar);
         tellWithFormat(gameIntroMsg, displayLevel);
         emptyLine();
         tellWithFormat(gameRuleMsg, moneyPraiseByLevel[gameLevel]);
         emptyLine();
         displayCurrentUserMoney();
         emptyLine();
         displayGameAnaysis();
         emptyLine();
         
         //Call a method that operates 5 unit questions pertaining to this level.
         playUnitQuestions();
         emptyLine();
         
         //Call a method that controls the 'level-ending' screen.
         displayLevelEnding();
         emptyLine();    
         
         //When game is over at this level, display a special message.
         if(isGameOver==true){
            tell(exitGameBar);
            displayGameAnaysis();
            emptyLine();
            tellWithFormat(goodByeMsg, userNickName);
            drawProgramFace();
         }
         
         //Simply show a section line.
         tell(sectionBar);
      }
   }
   
   //******** main game stream - end ***********//
   
   //******** sub stream - begin ***********//
   
   //This method initializes all variables that controls a game 'level'.
   public static void initializeLevel(){
   
      //Initialize the game 'level' variables only when it's a new level start.
      if(isNewLevelStart==true){
      
            //goToLevel is the level that will be played. The value on goToLevel has 
            //already been assigned at the ending of the previous level.
            gameLevel=goToLevel;  
            displayLevel=gameLevel+1; //displayLevel is always internal level number + 1.
            goToLevel=0; //re-initialize goToLevel for the next use.
            
            //Initialize success, fail, total counts, along with other game controller variables.
            successCount=0;
            failCount=0;
            totalCount=0;
            userScorePercent=0;
            isGameOver=false;
            stayAtZeroLevel=false;
      }
   }
  
   //This method plays 5 (=numOfUnitPerLevel) unit questions pertaining to each level.
   public static void playUnitQuestions(){
      tell(splitBar);
      for(int i=0; i<numOfUnitPerLevel; i++){ 
      
         /*A challenge question (if applies):
         //Time for challenge questions gets decided randomly using java Random api.
         //See the method seeIfChallengeTime().
         //Check to see if it's the time for challenge question, 
         //and play a next pending challenge question. */
         if(i%2==1){
            boolean isChallengeTime=seeIfChallengeTime(i);
            
            if(isChQMaxReached==false && isChallengeTime==true){
               //Call a function to play a challenge question.
               askChallengeQuestion(nextChQIndex);
               endofChallengeQuestion();
            }
         }
         /* Regular level questions:
         //Call a function to iterate and play all unit questions for the level. */
         
         boolean isFinished = false;
         repeatQuestion(isFinished, i);
         
         //Display the current score.
         displayCurrentScore();
         
         //Just a cosmetic display.
         emptyDoubleLine();
         tell(splitBar);
      }
   }
   
   //This method plays the unit questions pertaining to the current game level.
   public static void repeatQuestion(boolean isFinished, int unit){
      
      say("Question #"+(unit+1)+": \n\n");
      
      //isFinished gets true when the user either gets the right answer 
      //or decides to quit this specific question.
      while(isFinished==false){
      
         //Display the question to the user, and receive the user's response.
         String resp = askString(questions[gameLevel][unit]+setNumberGuide(unit));
         
         //Increase the total count of user attempt.
         totalCount++;
         
         //Check to see if the user's answer is correct.
         if(isRightAnswer(unit, resp)==true){
         
            //When the user's answer is correct, then deposit the game money earned.
            gameMoney+=moneyPraiseByLevel[gameLevel];
            
            //Increase the user's success count.
            successCount++;
            emptyLine();
            
            //Show the congratulation image and message.
            drawCongratsImage();
            emptyLine();
            tellWithFormat(rightAnsMsg, moneyPraiseByLevel[gameLevel]);
            emptyLine();
            
            //Show the moeny earned so far.
            displayCurrentUserMoney();
            
            isFinished=true;
            
         }else{
            
            //Increase the fail count.
            failCount++;
            
            //Display a message to show 'incorrect answer' and ask the user if she wants to continue.
            String retryAns = askString(wrongAnsMsg);
            
            //Validate the answer.
            while(!validateNotEmpty(retryAns) || !validateYN(retryAns)){
               retryAns = askString(sLevelYNMsg);            
            }
            
            char retry = retryAns.charAt(0);

            //Let the unit question finished or not, depending on the user's answer for retry, received above.
            if(retry=='y' || retry=='Y'){
               isFinished=false;
               
            }else{
               isFinished=true;
            }
            
            emptyLine();
         }
       } //end of a while loop.
   }   
   
   //This method controls the 'level-ending' screen.
   public static void displayLevelEnding(){
   
      //Display all the 'level' associated messages, scores, user money, and the next go-to level to the user.
      tellWithFormat(levelOverMsg, displayLevel);
      displayResultMessage(); //Success or Fail
      displayLevelAnaysis(); 
      displayCurrentScore(); 
      displayCurrentUserMoney();   
      
      //Call a method to determine if the level play was successful or not, 
      //and determine the next go-to level.
      decideDisplayGoToLevel();
      
      //Call a method to update cumulative game summary count (total attempt, success, failure).
      updateAllLevelCount();
      
      //Except at the last level, ask the user if she wants to continue playing the game.
      if(gameLevel!=(numOfLevel-1)){
         checkToContinueGame();
      }
   }   
   
   //This method determines if the 'level' play was successful or not, 
   //and set the proper values to the associated variables.
   //Also, this method determines what is the next goToLevel, considering the user's play outcome.
   public static void decideDisplayGoToLevel(){
   
      String msgFlag = "";
      
      //Set to start a new level after this.
      isNewLevelStart=true;
      
      //When the user was successful at this level.
      //Note: if the user got the questions right more than lvlPassPercent (50%), then it's successful.
      if(userScorePercent > lvlPassPercent){
      
         //Note: Level 5 is the highest (numOfLevel=5), and it's system level (gameLevel) is 4. 
         //System level represents an index of the array. So, it's an actual level - 1.
         
         if(gameLevel==(numOfLevel-1)){ 
            //When the played level was the highest level. 
         
            isGameOver=true;  //Game is over.
            goToLevel=gameLevel; //Stay at the same level.
            msgFlag = gameOverMsg; //Assign to the game-over message.
            isNewLevelStart=false; //We will not start a new level after this.
            
         }else{
            //When the played level was not the highest level.
            
            goToLevel=gameLevel+1; //Increase the level.
            msgFlag =levelUpMsg; //Assign to the level-up message.
         }
         
      }else{
      //When the user was not successful at this level.

         if(gameLevel==0){ 
            //When the played level was already the lowest level.
            
            stayAtZeroLevel=true; //Flag it to stay at the zero level.
            goToLevel=gameLevel; //Stay at the same level.
            msgFlag = zeroLevelMsg; //Assign to the zero-level-retry message.
            
         }else{ 
            //When the playged level was not the lowest level. 
                       
            goToLevel=gameLevel-1; //Decrease the level.
            msgFlag = levelDownMsg; //Assign to the level-down message.
         }
      }
      emptyLine();
      
      //Display the next level to go to.
      //Note: goToLevel is a system level (representing an array index). So its actual level to display is goToLevel + 1.
      tellWithFormat(msgFlag, (goToLevel+1)); 
      emptyLine();
   }  
   
   //This method controls challenge quesitons.
   //Challenge question initiation
   public static void askChallengeQuestion(int s){
      tell(chHeaderMsg);
      tell(chRuleMsg);
      emptyLine();
      //Check to see if the answer is right.
      if(isRightChAnswer(s, askString(chQuestions[s]+numGuideMsg))){
         //When the answer is right.
         emptyLine();
         tell(chCongratsMsg);
         doubleCurrentUserMoney(); //Double the money.
         displayCurrentUserMoney();
      }else{
         //When the answer is not right.
         emptyLine();
         tell(chFailMsg);
      }
   }
   
   //End messages of the challenge questions
   public static void endofChallengeQuestion() {
      emptyLine();
      tell(chEndMsg);
      tell(chTailMsg);
      emptyLine();
      assignNextChQIndex();
   }

   //******** sub stream - end ***********//
   
   //******** support operations - begin ***********//
   
   //This method asks a user which level she wants to start with.
   public static void getUserTryLevel(){
      String tryLevel = askString(startLevelInputMsg);
      //Validate the input.
      while(!checkIfInt(tryLevel) || !validateTryLevel(tryLevel)){
         if(!checkIfInt(tryLevel)){
            tryLevel = askString(startLevelNotIntMsg);
            continue;
         }
         if(!validateTryLevel(tryLevel)){
            tryLevel = askString(startLevelAgainMsg);
            continue;
         }
      }      
      int tryLevelInt = Integer.parseInt(tryLevel);
      gameLevel=tryLevelInt-1; //gameLevel is a system level, so it's one down the level user enters.
      displayLevel=tryLevelInt;
   }
   
   //This method validates user input for try level.
   public static boolean validateTryLevel(String tryLevel){
      if(Integer.parseInt(tryLevel) <= numOfLevel){
            return true;
      }else{
            return false;
      }
   }
   
   //This method asks a user for her grade, and validates the value.
   public static void getUserGrade(){
      String grade = askStringWithFormat(userGradeMsg, userNickName);
      //Check if the entered data is valid. If not, ask again.
      while(!checkIfInt(grade) || !validateGrade(grade)){
         grade = askString(userGradeAgainMsg);
      }        
      userGrade = grade;
   }
   
   //This method checks if the given grade is acceptable/valid grade or not.
   public static boolean validateGrade(String grade){
      if(Integer.parseInt(grade) <= kindergartenGradeMax){
            return true;
      }else{
            return false;
      }
   }   
   
   //This method asks user to start at the same level as her grade.
   //If she desires to start at a different level, then the program asks for the desired level,
   //and assign it to the start level.
   public static void decideGameStartLevel(){
      String ifSameLevelAns = askStringWithFormat(startLevelMsg, userGrade);
      //Validate the answer.
      while(!validateNotEmpty(ifSameLevelAns) || !validateYN(ifSameLevelAns)){
             ifSameLevelAns = askString(sLevelYNMsg);
      }
      char ifSameLevel = ifSameLevelAns.charAt(0);      
      if(ifSameLevel=='y' || ifSameLevel=='Y'){
            gameLevel=Integer.parseInt(userGrade)-1;
            displayLevel=Integer.parseInt(userGrade);
      }else if(ifSameLevel=='n' || ifSameLevel=='N'){
            getUserTryLevel();
      }
   }

   //This method checks if the input is a valid integer or not.
   public static boolean checkIfInt(String input){
      try{
         Integer.parseInt(input);
      }catch(NumberFormatException ex){
         return false;
      }
      return true;
   }

   //This method displays a result message determined by a play result.
   public static void displayResultMessage(){
      if(userScorePercent>lvlPassPercent){
         tell(congratsMsg);
      }else{
         tell(encourageMsg);
      }
      emptyLine();
   }

   //This method uses Random api and checks to see if it's time for challenge questions.
   public static boolean seeIfChallengeTime(int idx){
   
      //use random api and decide if it's time for challenge questions.
      Random random = new Random();
      int rand = random.nextInt(99)+1;
      if((idx + rand)%2==0){
         return true;
      }else{
         return false;
      }
   }

   //This method assigns a next challenge question.
   public static void assignNextChQIndex(){
      if(nextChQIndex==numOfChallenge){
         isChQMaxReached=true;
         nextChQIndex=0;   
      }else{
         nextChQIndex++;
      }
   }
   
   //This method controls number guild message. It's set on at every even number of iterations of unit questions.
   public static String setNumberGuide(int i){
      if(i%2==0){
         return numGuideMsg;
      }else{
         return "";
      }
   }
   
   //This method calculates and displays the current score.
   public static void displayCurrentScore(){
      userScorePercent=((successCount*1.0)/numOfUnitPerLevel)*100;
      tellWithFormat(currentScoreMsg,userScorePercent);
   }
   
   //This method doubles the game money.
   public static void doubleCurrentUserMoney(){
      gameMoney=gameMoney*2;
   }   
   
   //This method displays the amount of accummulated game money.
   public static void displayCurrentUserMoney(){
      tellWithFormat(userMoneyMsg, gameMoney);
   }   
   
   //This method displays play results of the level.
   public static void displayLevelAnaysis(){ 
      tellWithFormat(lvlAnaysisMsg, totalCount, successCount, failCount); 
      emptyLine();
   } 
   
   //This method displays cumulative play results.
   public static void displayGameAnaysis(){  
      tellWithFormat(gameAnaysisMsg, allLvlCount, allLvlSuccess, allLvlFail); 
   }  
   
   //This method updates cumulative play results.
   public static void updateAllLevelCount(){
      allLvlSuccess+=successCount;
      allLvlFail+=failCount;
      allLvlCount+=totalCount;
   } 
   
   //This method checks if the given challenge question answer is correct or not.
   public static boolean isRightChAnswer(int k, String resp){
      String ans = chAnswers[k];
      boolean ret=false;
      if(resp.equals(ans)){
         ret=true;
      }else{
         ret=false;
      }
      return ret;
   }
   
   //This method checks if the given answer is correct or not.
   public static boolean isRightAnswer(int unit, String resp){
      String ans = answers[gameLevel][unit];
      boolean ret=false;
      if(resp.equals(ans)){
         ret=true;
      }else{
         ret=false;
      }
      return ret;
   }
   
   //This method controls a game exit after asking user if she wants to continue or quit.
   public static void checkToContinueGame(){
      String answer = askString(exitGameMsg);
      while(!validateNotEmpty(answer) || !validateYN(answer)){
         answer = askString(sLevelYNMsg);
      }
      char ans = answer.charAt(0);
      if(ans=='n' || ans=='N'){
         isGameOver=true;
      }
   }
   
   //This method validates an empty value.
   public static boolean validateNotEmpty(String s){
      if(!s.trim().equals("")){
         return true;
      }else{
         return false;
      }
   }
   
   //This method validates an empty value.
   public static boolean validateNotEmptyChar(char s){
      if(!Character.toString(s).trim().equals("")){
         return true;
      }else{
         return false;
      }
   }
   
   //This method validates the input value.
   public static boolean validateYN(String str){
      char s = str.charAt(0);
      if(s=='y' || s=='Y' || s=='n' || s=='N'){
         return true;
      }else{
         return false;
      }
   }
   
   //This method display a messsage to the user and receives a user input.
   public static String askString(String s){
      System.out.print(s+" "); 
      return sc.nextLine();        
   }
   
   //This method display a messsage to the user and receives a user input.
   public static String askStringWithFormat(String s, String param){
      System.out.printf(s+" ", param); 
      return sc.nextLine();        
   }
   
   //This method display a messsage to the user and receives a user input.
   public static char askChar(String s){
      System.out.print(s+" "); 
      String ans = sc.nextLine();
      return ans.charAt(0);        
   }
   
   //This method display a messsage to the user and receives a user input.
   public static char askCharWithFormat(String s, String param){
      System.out.printf(s+" ", param); 
      String ans = sc.nextLine();
      return ans.charAt(0);        
   }
   
   //This method prints an empty line.
   public static void emptyLine(){
      System.out.println();
   }
   
   //This method prints double empty lines.
   public static void emptyDoubleLine(){
      System.out.print("\n\n");
   }
   
   //This method prints a message.
   public static void say(String s){
      System.out.print(s);
   }
   
   //This method prints a message.
   public static void sayWithFormat(String s, String param){
      System.out.printf(s, param);         
   }
   
   //This method prints a message.
   public static void sayWithFormat(String s, double param){
      System.out.printf(s, param);         
   }
   
   //This method prints a message.
   public static void tell(String s){
      System.out.println(s);         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, String param){
      System.out.printf(s+"\n", param);         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, double param){
      System.out.printf(s+"\n", param);         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, int param){
      System.out.printf(s+"\n", Integer.toString(param));         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, String param1, String param2){
      System.out.printf(s+"\n", param1, param2);         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, String param1, int param2){
      System.out.printf(s+"\n", param1, Integer.toString(param2));         
   }
   
   //This method prints a message.
   public static void tellWithFormat(String s, int param1, int param2, int param3){
      System.out.printf(s+"\n", Integer.toString(param1), Integer.toString(param2), Integer.toString(param3));         
   }

   //This method prints a program face.
   public static void drawProgramFace(){
         tell("       *****       ");
         tell("    **       **    ");
         tell("   *           *   ");
         tell("  *    |   |    *  ");
         tell("  *             *  ");
         tell("  **  *     *   *  ");
         tell("   **   ***    *   ");
         tell("     **     **     ");
         tell("       *****       ");   
   }
   
   //This method prints a congratulation image.
   public static void drawCongratsImage(){
         tell("     /\\   ");
         tell("    //\\\\ ");
         tell("   \"\"\"\"\"\"  YAY! CONGRATULATIONS !!");
         tell("     ||              YOU GOT IT RIGHT !!");
         tell("  --------");
         tell("  \\\\    //");
         tell("*************");   
   }
   
   //******** support operations - end ***********//

}