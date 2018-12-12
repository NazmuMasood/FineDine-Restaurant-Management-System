import java.io.*;
import java.util.*; 
public class tttest{
  static String sTotal = "";//Contains all the given inputs in a concatanated string
  static ArrayList<String> eachStrCommand = new ArrayList<String>();//Contains each line of commands
  
  static ArrayList<String> matOp = new ArrayList<String>();
  
  static ArrayList<String> logicOp = new ArrayList<String>();
  
  static ArrayList<String> otherOp = new ArrayList<String>();
  
  static ArrayList<String> keyword = new ArrayList<String>();
  
  static int intChk=0,doubChk=0;
  static ArrayList<String> var = new ArrayList<String>();  
  static ArrayList<String> intDoubChk = new ArrayList<String>();
  static ArrayList<String> storeWorkChk = new ArrayList<String>();
  static ArrayList<String> num = new ArrayList<String>();
  static ArrayList<String> expr = new ArrayList<String>();
  
  
  static ArrayList<Integer> intA=new ArrayList<Integer>();
  static ArrayList<Double> doubleA=new ArrayList<Double>();
  
  
  static int precedenceLevel(char op) {
    switch (op) {
      case '+':
      case '-':
        return 0;
      case '*':
      case '/':
      case '%':
        return 1;
      case '^':
        return 2;
      default:
        throw new IllegalArgumentException("Operator unknown: " + op);
    }
  }
  public static void main(String[]args){
    String s = "a*(b-cod)^2+(3+cod)+20+(cod+20)+cod";
    // String s = "x";
    // Stack st1 = new Stack();
    // st1.push("a");
    //  st1.push(20);
    // char c = s.charAt(0);
    // String m=String.valueOf(c);
    
    //String a_letter = Character.toString(text.charAt(0));
    
//    String number = "10";
// int result = Integer.parseInt(number);
    
//    String s="23.6";  
//    double d=Double.parseDouble("23.6"); 
    
    /////a = a.replaceAll("\\s","");
//    System.out.println(c);
//    System.out.println(m);
    //  char op1='+',op2='/';
    //  System.out.println(precedenceLevel(c));
//    s = s.replaceAll("int","");
//    System.out.println(s);
//    System.out.println(st1.pop());
//    System.out.println(st1.pop());
    
//    int p =0;
//    while(p<5){
//      label:
//        System.out.println(p);
//        p++;
//        break;
//        
//        //System.out.println(p);p++;
//        //String numberAsString = Integer.toString(number);
//        //int intResult = (int) Math.pow(2, 3);
//    }
//    list.set( 2, "New" );
//    if(s.matches(".*[-+x/^$%()].*")){System.out.println("math op ase");}
//    else{System.out.println("oNoooo");}
    
//    if(s.matches("-?\\d+(\\.\\d+)?")){System.out.println("math op ase");}
//    else{System.out.println("oNoooo");}
    var.add("a");var.add("b");var.add("cod");String sD = s+"$";
    int matched = 0; int varMatch = 0; 
    while(varMatch<s.length()){
      int count = 1; 
      String sTemp=Character.toString(s.charAt(varMatch));
      
      String sNext = Character.toString(sD.charAt(varMatch+1));
      if(Character.toString(sTemp.charAt(sTemp.length()-1)).matches("[a-zA-Z].*")||Character.toString(sTemp.charAt(sTemp.length()-1)).matches("-?\\d+(\\.\\d+)?")){
        while(!sNext.matches(".*[-+*/^%$()].*")){
          varMatch++;
          sTemp=sTemp+sNext;
          sNext=Character.toString(sD.charAt(varMatch+1));
        }
      }
      
      if(sTemp.matches("[a-zA-Z].*")){
        count=0;          
        for(int j=0;j<var.size();j++){
          if(sTemp.equals(var.get(j))){count=1;}
        }System.out.println("var "+sTemp+" and count value "+count);
        if(count==0){matched=0;System.out.println("matched value "+matched);break;}
        if(count==1){matched=1;System.out.println("matched value "+matched);}
      }
      if(sTemp.matches("-?\\d+(\\.\\d+)?")){matched=1;System.out.println("var "+sTemp+" and matched value "+matched);}
      if(sTemp.matches(".*[-+*/^$%()].*")){
        if(sTemp.equals(")")){matched=1;}
        else{matched=0;}System.out.println("var "+sTemp+" and matched value "+matched);
      }
      varMatch++;
    }
    
    if(matched==0){System.out.println("Compilation error");}
    if(matched==1){
      System.out.println("Good to go"); }
    
    System.out.println("Given expression : "+s);
    ArrayList<String> postV = makePostV(s);
    System.out.print("Postfix expression : ");
    for(int j=0;j<postV.size();j++){System.out.print(postV.get(j)+" ");}
    System.out.println();
//    
//    intA.add(3);intA.add(4);intA.add(3);
    num.add("3");num.add("5");num.add("2");
    intDoubChk.add("int");intDoubChk.add("int");intDoubChk.add("int");
    // doubleA.add(1.0);doubleA.add(2);doubleA.add(2);
    var.add("a");var.add("b");var.add("c");
    
    System.out.println("Sum is "+calIntPV(postV)); 
    // System.out.println("Sum is "+calDoublePV(postV));
  }
  
  //"makePostV" method produces postfix expression   
  public static ArrayList<String> makePostV(String s){
    String sD = s+"$";
    Stack<String> st1 = new Stack<String>();//Operators and parenthesis are stored temporarily on string stack "st1"
    ArrayList<String> postV = new ArrayList<String>();
    int p=0;//Pointer for each character in the expression; Gets incremented 
    
    while(p<s.length()){
      int count = 0;//Count for making sure only one of the following "if"s are executed in one iteration     
      String s1=Character.toString(sD.charAt(p));
      
      String sNext = Character.toString(sD.charAt(p+1));
      if(Character.toString(s1.charAt(s1.length()-1)).matches("[a-zA-Z].*")||Character.toString(s1.charAt(s1.length()-1)).matches("-?\\d+(\\.\\d+)?")){
        while(!sNext.matches(".*[-+*/^%$()].*")){
          p++;
          s1=s1+sNext;
          sNext=Character.toString(sD.charAt(p+1));
        }
      }
      
      //For identifiers in the expression      
      if((s1.matches("[a-zA-Z].*"))&&count==0){postV.add(s1);count++;}
      
      //For numerical values given directly in the expression
      if(s1.matches("-?\\d+(\\.\\d+)?")&&count==0){postV.add(s1);count++;}
      
      //For parenthesis in the expression
      if((s1.equals("(")||s1.equals(")"))&&count==0){ 
        if(s1.equals("(")){st1.push("(");}
        if(s1.equals(")")){
          while(!st1.peek().equals("(")&&!st1.empty()){String currOp = st1.pop();postV.add(currOp);}
          st1.pop();
        }count++;
      }  
      
      //For operators in the expression
      if(s1.equals("+")||s1.equals("-")||s1.equals("%")||s1.equals("/")||s1.equals("*")||s1.equals("^")){     
        if(st1.empty()&&count==0){st1.push(s1);count++;}
        
        if(!st1.empty()&&count==0){
          if(st1.peek().equals("(")){st1.push(s1);count++;}          
          
          String s2=st1.peek();
          char op1 = s2.charAt(0);
          char op2=s1.charAt(0);       
          
          if(count==0){
            while((precedenceLevel(op1)>=precedenceLevel(op2))&&!st1.empty()){
              String currOp=st1.pop();            
              postV.add(currOp);
              if(!st1.empty()&&st1.peek().equals("(")){break;}
              if(!st1.empty()){s2=st1.peek();op1 = s2.charAt(0);}
            } st1.push(s1);count++;      
          }
          
          if((precedenceLevel(op1)<precedenceLevel(op2))&&count==0){
            st1.push(s1);
            count++;
          }          
        }     
      }      
      p++;  
    }
    
    //At the end pops operators if remained in the stack
    while(!st1.empty()){
      String currOp=st1.pop();
      postV.add(currOp);
    }       
    return postV;//Returns the postfix expression
  }
  
////"calPV" method calculates postfix expression
  public static double calDoublePV(ArrayList<String> arL){
    ArrayList<String> postV = new ArrayList<String>();
    postV = arL;
    Stack<String> st2 = new Stack<String>();//Operands are stored temporarily on string stack st2
    double summ=0; //Stores the answer to the expression 
    
    for(int i=0;i<postV.size();i++){
      String s = postV.get(i);
      
      //Deals with the operands (identifiers and numerical values) in the expressions
      //If operand, push them in the stack
      if(s.matches("[a-zA-Z].*")||s.matches("-?\\d+(\\.\\d+)?")){st2.push(s);}
      
      //Deals with the operators in the expression
      //If operator, pop two operands from the stack
      else{
        double n1=0,n2=0;int index1=-1,index2=-1;
        String ss2 = st2.pop();
        String ss1 = st2.pop();
        
        //If the operands are identifiers
        if(!ss1.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss1.equals(var.get(j))){
              index1=j;
              break;
            }
          }
          // System.out.println("index1 is "+index1);
          if(intDoubChk.get(index1).equals("int")){String nTemp= num.get(index1);n1=Integer.parseInt(nTemp);}
          if(intDoubChk.get(index1).equals("double")){String nTemp= num.get(index1);n1=Double.parseDouble(nTemp);}
        }        
        if(!ss2.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss2.equals(var.get(j))){
              index2=j;
              break;
            }
          }
          //System.out.println("index2 is "+index2);
          if(intDoubChk.get(index2).equals("int")){String nTemp= num.get(index2);n2=Integer.parseInt(nTemp);}
          if(intDoubChk.get(index2).equals("double")){String nTemp= num.get(index2);n2=Double.parseDouble(nTemp);}
        }
        
        //If the operands are numerical values
        if(ss1.matches("-?\\d+")){ n1=Integer.parseInt(ss1);}
        if(ss1.matches("-?\\d+(\\.\\d+)?")){ n1=Double.parseDouble(ss1);}
        
        if(ss2.matches("-?\\d+")){ n2=Integer.parseInt(ss2);}
        if(ss2.matches("-?\\d+(\\.\\d+)?")){ n2=Double.parseDouble(ss2);}
        
        //Calculates the operands by the operator
        if(s.equals("+")){summ=n1+n2;st2.push(Double.toString(summ));}
        if(s.equals("-")){summ=n1-n2;st2.push(Double.toString(summ));}
        if(s.equals("/")){summ=n1/n2;st2.push(Double.toString(summ));}
        if(s.equals("*")){summ=n1*n2;st2.push(Double.toString(summ));}
        if(s.equals("%")){summ=n1%n2;st2.push(Double.toString(summ));} 
        if(s.equals("^")){summ=Math.pow(n1, n2);st2.push(Double.toString(summ));}
        
      }            
    }   return summ; //Returns the answer to the expression
  }  
  
  public static int calIntPV(ArrayList<String> arL){
    ArrayList<String> postV = new ArrayList<String>();
    postV = arL;
    Stack<String> st2 = new Stack<String>();//Operands are stored temporarily on string stack st2
    int summ=0; //Stores the answer to the expression 
    
    for(int i=0;i<postV.size();i++){
      String s = postV.get(i);
      
      //Deals with the operands (identifiers and numerical values) in the expressions
      //If operand, push them in the stack
      if(s.matches("[a-zA-Z].*")||s.matches("-?\\d+(\\.\\d+)?")){st2.push(s);}
      
      //Deals with the operators in the expression
      //If operator, pop two operands from the stack
      else{
        int n1=0,n2=0;int index1=-1,index2=-1;
        String ss2 = st2.pop();
        String ss1 = st2.pop();
        
        //If the operands are identifiers
        if(!ss1.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss1.equals(var.get(j))){
              index1=j;
              break;
            }
          }
          String nTemp= num.get(index1);n1=Integer.parseInt(nTemp);
        }        
        if(!ss2.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss2.equals(var.get(j))){
              index2=j;
              break;
            }
          }
          String nTemp= num.get(index2);n2=Integer.parseInt(nTemp);
        }
        
        //If the operands are numerical values
        if(ss1.matches("-?\\d+(\\.\\d+)?")){ n1=Integer.parseInt(ss1);}
        if(ss2.matches("-?\\d+(\\.\\d+)?")){ n2=Integer.parseInt(ss2);}
        
        //Calculates the operands by the operator
        if(s.equals("+")){summ=n1+n2;st2.push(Integer.toString(summ));}
        if(s.equals("-")){summ=n1-n2;st2.push(Integer.toString(summ));}
        if(s.equals("/")){summ=n1/n2;st2.push(Integer.toString(summ));}
        if(s.equals("*")){summ=n1*n2;st2.push(Integer.toString(summ));}
        if(s.equals("%")){summ=n1%n2;st2.push(Integer.toString(summ));} 
        if(s.equals("^")){summ=(int) Math.pow(n1, n2);st2.push(Integer.toString(summ));}
        
      }            
    }   return summ; //Returns the answer to the expression
  }
  
  //"makePostV" method produces postfix expression   
//  public static ArrayList<String> makePostV(String s){
//    String sD = s+"$";
//    Stack<String> st1 = new Stack<String>();//Operators and parenthesis are stored temporarily on string stack "st1"
//    ArrayList<String> postV = new ArrayList<String>();
//    int p=0;//Pointer for each character in the expression; Gets incremented 
//    
//    while(p<s.length()){
//      int count = 0;//Count for making sure only one of the following "if"s are executed in one iteration     
//      String s1=Character.toString(sD.charAt(p));
//      
//      String sNext = Character.toString(sD.charAt(p+1));
//      if(Character.toString(s1.charAt(s1.length()-1)).matches("[a-z&&[^x]].*")||Character.toString(s1.charAt(s1.length()-1)).matches("[A-Z].*")||Character.toString(s1.charAt(s1.length()-1)).matches("-?\\d+(\\.\\d+)?")){
//        while(!sNext.matches(".*[-+x/^%$()].*")){
//          p++;
//          s1=s1+sNext;
//          sNext=Character.toString(sD.charAt(p+1));
//        }
//      }
//      System.out.println("s1 is "+s1); 
//      //For identifiers in the expression      
//      if((s1.matches("[a-z&&[^x]].*")||s1.matches("[A-Z].*"))&&count==0){postV.add(s1);count++;}
//      
//      //For numerical values given directly in the expression
//      if(s1.matches("-?\\d+(\\.\\d+)?")&&count==0){postV.add(s1);count++;}
//      
//      //For parenthesis in the expression
//      if((s1.equals("(")||s1.equals(")"))&&count==0){ 
//        if(s1.equals("(")){st1.push("(");}
//        if(s1.equals(")")){
//          while(!st1.peek().equals("(")&&!st1.empty()){String currOp = st1.pop();postV.add(currOp);}
//          st1.pop();
//        }count++;
//      }  
//      
//      //For operators in the expression
//      if(s1.equals("+")||s1.equals("-")||s1.equals("%")||s1.equals("/")||s1.equals("x")||s1.equals("^")){     
//        if(st1.empty()&&count==0){st1.push(s1);count++;}
//        
//        if(!st1.empty()&&count==0){
//          if(st1.peek().equals("(")){st1.push(s1);count++;}          
//          
//          String s2=st1.peek();
//          char op1 = s2.charAt(0);
//          char op2=s1.charAt(0);       
//          
//          if(count==0){
//            while((precedenceLevel(op1)>=precedenceLevel(op2))&&!st1.empty()){
//              String currOp=st1.pop();            
//              postV.add(currOp);
//              if(st1.peek().equals("(")){break;}
//              if(!st1.empty()){s2=st1.peek();op1 = s2.charAt(0);}
//            } st1.push(s1);count++;      
//          }
//          
//          if((precedenceLevel(op1)<precedenceLevel(op2))&&count==0){
//            st1.push(s1);
//            count++;
//          }          
//        }     
//      }      
//      p++;  
//    }
//    
//    //At the end pops operators if remained in the stack
//    while(!st1.empty()){
//      String currOp=st1.pop();
//      postV.add(currOp);
//    }       
//    return postV;//Returns the postfix expression
//  }
}