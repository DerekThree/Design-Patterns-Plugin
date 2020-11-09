public class InsideOutAssembler implements Assembler {
   private Component co = new Component();

   public void buildPart1() { 
      co.add(new Inside());
   }
   public void buildPart2() { 
      co.add(new Outside());
   }
   public Component getResult() {
      return co;
   }
}
