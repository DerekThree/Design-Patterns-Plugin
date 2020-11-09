public class LeftRightAssembler implements Assembler {
   private Component co = new Component();

   public void buildPart1() { 
      co.add(new LeftSide());
   }
   public void buildPart2() { 
      co.add(new InnerLeftPart());
   }
   public void buildPart3() { 
      co.add(new InnerRightPrt());
   }
   public void buildPart4() { 
      co.add(new RightSide());
   }
   public Component getResult() {
      return co;
   }
}
