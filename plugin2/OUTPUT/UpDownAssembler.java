public class UpDownAssembler implements Assembler {
   private Component co = new Component();

   public void buildPart1() { 
      co.add(new UpperPart());
   }
   public void buildPart2() { 
      co.add(new MiddlePart());
   }
   public void buildPart3() { 
      co.add(new LowerPart());
   }
   public Component getResult() {
      return co;
   }
}
