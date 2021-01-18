import java.util.Random;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

   public static void main(String[] args) {
      JDABuilder builder = JDABuilder.createDefault("ODAwNTk3OTc4NzQ1OTI5NzU5.YAUc-A.8npCTO7FpyQmtjYUHPSNSBnrqrA");

      builder.addEventListeners(new Main());

      try {
         builder.build();
      } catch (LoginException e) {
         e.printStackTrace();
      }

   }

   @Override
   public void onMessageReceived(MessageReceivedEvent event) {
      System.out.println("We Received a message from "
            + event.getAuthor().getName() + ": "
            + event.getMessage().getContentDisplay() );
      if(event.getMessage().getContentRaw().toLowerCase().equals("fish help")
         || event.getMessage().getContentRaw().toLowerCase().equals("fish") ) {
         event.getChannel().sendMessage("Welcome to **THE FISHZONE.**" +
               "\nIt's a good day. *You are fishing.*" +
               "\nTo cast your line, type 'fish cast <number 1-100>' " +
               "\nThe closer your guess is to the **FishRNG**:tm:, the better your catch!").queue();
      }

      if(event.getAuthor().isBot()) {
         return;
      }

      if(event.getMessage().getContentRaw().toLowerCase().startsWith("fish cast ")) {
         String guess = "";
         String invalidGuess = "Hey! Invalid cast! Remember, only a number 1-100!";

         try {
            guess = event.getMessage().getContentRaw().substring(10);
            int guessInt = Integer.parseInt(guess);
            event.getChannel().sendMessage("*Whoosh!* You cast your line.").queue();

            if (guessInt > 0 && guessInt <= 100) {
               event.getChannel().sendMessage(castLine(guessInt)).queue();
            }
            else event.getChannel().sendMessage(invalidGuess).queue();


         } catch (StringIndexOutOfBoundsException e) {
            event.getChannel().sendMessage(invalidGuess).queue();
         } catch (NumberFormatException e) {
            event.getChannel().sendMessage(invalidGuess).queue();
         }
      }



   }

   private String castLine(int guessInt) {
      Random rng = new Random();
      int min = 1;
      int max = 100;

      int fishLocation = (int) Math.round(Math.random() * (max - min + 1) + min);
      int accuracy = 100 - Math.round(Math.abs(guessInt - fishLocation));

      Fish outcome = new Fish(accuracy);

      return ":fish_cake:\tYour guess: " + guessInt
            + "\n:fish:\tFish Location: " + fishLocation
            + "\n:medal:\tYour accuracy: " + accuracy + "%"
            + "\n\n" + ":fishing_pole_and_fish: YOU CAUGHT: " + outcome.getLoot();
   }


}
