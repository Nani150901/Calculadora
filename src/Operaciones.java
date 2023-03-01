import java.util.Stack;

public class Operaciones {
	private static String depurar(String s) {
	    s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
	    s = "(" + s + ")";
	    String simbols = "+-*^/()";
	    String str = "";
	    //Deja espacios entre operadores
	    for (int i = 0; i < s.length(); i++) {
	      if (simbols.contains("" + s.charAt(i))) {
	        str += " " + s.charAt(i) + " ";
	      }else str += s.charAt(i);
	    }
	    return str.replaceAll("\\s+", " ").trim();
	  }
	 //Jerarquia de los operadores
	  private static int pref(String op) {
	    int prf = 99;
	    if (op.equals("^")) prf = 5;
	    if (op.equals("*") || op.equals("/")) prf = 4;
	    if (op.equals("+") || op.equals("-")) prf = 3;
	    if (op.equals(")")) prf = 2;
	    if (op.equals("(")) prf = 1;
	    return prf;
	  }
	  private static int evaluar(String op, String n2, String n1) {
		    int num1 = Integer.parseInt(n1);
		    int num2 = Integer.parseInt(n2);
		    if (op.equals("+")) return (num1 + num2);
		    if (op.equals("-")) return (num1 - num2);
		    if (op.equals("*")) return (num1 * num2);
		    if (op.equals("/")) return (num1 / num2);
		    if (op.equals("%")) return (num1 % num2);
		    if (op.equals("^")) return (int) (Math.pow(num1, num1));
		    return 0;
		  }
	public static String operaciones(String txtRsultado) {
		 //Depurar la expresion algebraica
	    String expr = depurar(txtRsultado);
	    String[] arrayInfix = expr.split(" ");
	    //Declaración de las pilas
	    Stack < String > pilaEntrada = new Stack < String > (); //Pila entrada
	    Stack < String > pilaOperadores = new Stack < String > (); //Pila temporal para operadores
	    Stack < String > pilaSalida = new Stack < String > (); //Pila salida
	  //Añadir la array a la Pila de entrada
	    for (int i = arrayInfix.length - 1; i >= 0; i--) {
	      pilaEntrada.push(arrayInfix[i]);
	    }
	    try {
		      //Infijo a Postfijo
		      while (!pilaEntrada.isEmpty()) {
		        switch (pref(pilaEntrada.peek())){
		          case 1:
		        	  pilaOperadores.push(pilaEntrada.pop());
		            break;
		            case 2:
			            while(!pilaOperadores.peek().equals("(")) {
			            	pilaSalida.push(pilaOperadores.pop());
			            }
			            pilaOperadores.pop();
			            pilaEntrada.pop();
			            break;
		          case 3:
		          case 5:
		          case 4:
		            while(pref(pilaOperadores.peek()) >= pref(pilaEntrada.peek())) {
		            	pilaSalida.push(pilaOperadores.pop());
		            }
		            pilaOperadores.push(pilaEntrada.pop());
		            break;
		          default:
		        	  pilaSalida.push(pilaEntrada.pop()); 
		        } 
		      }
		      //Eliminacion de `impurezas´ en la expresiones algebraicas
		      String  postfix = pilaSalida.toString().replaceAll("[\\]\\[,]", ",");
		      postfix = pilaSalida.toString().replaceAll("[\\]\\[,]", "");
		    //Evaluación Postfija
			    String[] post = postfix.split(" ");
			    Stack < String > postFix = new Stack < String > ();
			    for (int i = post.length - 1; i >= 0; i--) {
			    	postFix.push(post[i]);
			      }
			    String operadores = "+-*^/%";
			    while (!postFix.isEmpty()) {
			      if (operadores.contains("" + postFix.peek())) {
			    	  pilaOperadores.push(evaluar(postFix.pop(), pilaOperadores.pop(), pilaOperadores.pop()) + "");
			      }else {
			    	  pilaOperadores.push(postFix.pop());
			      }
			    }
			   // System.out.println("Resultado: " + pilaOperadores.peek());
		
	    }catch(Exception ex){ 
		      System.out.println("Error en la expresión algebraica");
		      System.err.println(ex);
		    }
		return ("" + pilaOperadores.peek());
		  
	    
	}
	public static void main(String[] args) {
	}

	
}
