import java.util.Random;

public class Fish {
   String loot;

   public Fish(int accuracy) {
      char rarity = rarityFinder(accuracy);
      loot = lootMaker(rarity);
   }

   public String getLoot() {
      return loot;
   }

   private String lootMaker(char rarity) {
      switch (rarity) {
         case 'L':
            return "**LEGENDARY **" + ownerGen()
               + " " + prefixGen() + " " + fishGen() + " " + suffixGen();
         case 'E':
            return "**EPIC **" + prefixGen()
                  + " " + fishGen() + " " + suffixGen();
         case 'U':
            return "**UNCOMMON **" + prefixGen() + " " + fishGen();
         case 'C':
            return "**COMMON **" + fishGen();
         case 'T':
            return "**TRASH, LOSER! : **" + trashGen();
         default: return "Nothing. **PATHETIC!**";
      }
   }

   private String trashGen() {
      return "INSERT TRASH";
   }

   private String suffixGen() {
      String[] suffixes = new String[] { "Last Serpent", "Molten Core", "Dark Wrath", "Blue Fox",
            "Bimbo", "Grand Insight", "Lost Voices", "Broken Leg", "Dementia Patient"};
      int min = 0;
      int max = suffixes.length;
      return "of the " + suffixes[Math.abs(getRandom(min,max) - 1)];
   }

   private String fishGen() {
      String[] fish = new String[] { "Carp", "Salmon", "Trout", "Goldfish", "Shark", "Whale", "Pufferfish", "Blowfish"
      , "Anglerfish", "Dolphin", "Clownfish", "Eel", "Anchovy", "Cod", "Tilapia", "Herring"};
      int min = 1;
      int max = fish.length;
      return fish[Math.abs(getRandom(min,max) - 1)];
   }

   private String prefixGen() {
      String[] prefixes = new String[] { "Magical", "Horny", "Fishy", "Fat", "Obese", "Bony", "Grimy", "THICC",
      "Sassy", "Droopy", "Stinky", "Poopy", "Wild", "Spiny", "Tiny", "Huge", "Poopy"};
      int min = 1;
      int max = prefixes.length;
      return prefixes[Math.abs(getRandom(min,max) - 1)];
   }

   private String ownerGen() {
      String[] owners = new String[]{"Solomon's", "Egg's", "Drake's", "Midi's", "Krydon's", "Echo's", "Achi's",
      "Galpo's", "Midija's", "Zezima's"};
      int min = 1;
      int max = owners.length;
      return owners[Math.abs(getRandom(min,max) - 1)];
   }


   private static int getRandom(int min, int max) {

      if (min >= max) {
         throw new IllegalArgumentException("max must be greater than min");
      }

      Random r = new Random();
      return r.nextInt((max - min) + 1) + min;
   }

   private char rarityFinder(int accuracy) {
      if (accuracy >= 97) { return 'L'; }
      if (accuracy >= 86 && accuracy <= 96) { return 'E'; }
      if (accuracy >= 72 && accuracy <= 85) { return 'U'; }
      if (accuracy >= 59 && accuracy <= 71) { return 'C'; }
      else if (accuracy >= 40 && accuracy <= 58) { return 'T'; }
      else return 'X';
   }
}
