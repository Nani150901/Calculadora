import static java.awt.Font.PLAIN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame{
	GridBagConstraints gbc= new GridBagConstraints();
	GridBagLayout gbl= new GridBagLayout();
	JLabel txtResultado;
	 int totalBtn=20;
	    JButton botones[] = new JButton[totalBtn];
	    String textoBotones[] = {"c","/","*","ce","7","8","9","-","4","5","6","+","1","2","3","^","%","0",".","="};
	    int numerosBotones[] = {17, 12, 13, 14, 8, 9, 10, 4, 5, 6};
	    boolean nuevoNumero = true;
		 boolean puntoDecimal = false;
	public Ventana() {
		getContentPane().setLayout(gbl); 
        setTitle("Calculadora"); 
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setBackground(Color.BLACK); 
        setVisible(true); 
		pantalla();
		btnNum();
		
		
		txtResultado.setText(Operaciones.operaciones("(3*4-(2^5))* 4/2"));//cambiar
	}//constructor
	
	public void metodoMagico(Component c,int x, int y, int w, int h) {
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(c, gbc);
	}
	
	public void estiloBtn(JButton c) {
		c.setFont(new Font("MONOSPACED",PLAIN,30));
		c.setOpaque(true); //Para poder darle un color de fondo
        c.setBackground(Color.DARK_GRAY); //Color de fondo
        c.setForeground(Color.WHITE); //Color de fuente
        c.setBorder(new LineBorder(Color.BLUE)); //Borde
        add(c);
	}
	
	public void pantalla() {
		txtResultado = new JLabel("0"); //Inicio JLabel
		metodoMagico(txtResultado,0,0,4,1);
        txtResultado.setOpaque(true); //Para poder darle un color de fondo
        txtResultado.setBackground(Color.WHITE); //Color de fondo
        txtResultado.setForeground(Color.BLUE); //Color de fuente
        txtResultado.setBorder(new LineBorder(Color.DARK_GRAY)); //Borde
        txtResultado.setFont(new Font("MONOSPACED", PLAIN, 30)); //Fuente
        txtResultado.setHorizontalAlignment(SwingConstants.RIGHT); //Alineamiento horizontal derecha
        add(txtResultado); //A�ado el JLabel al JFrame
	}
	
	public void btnNum() {
		   int yBotones[] = {1, 1,  1,  1,  2,  2,  2,  2,  3,  3,  3,  3,  4,  4,  4,  4,  5, 5, 5, 5};
		   int xBotones[] = {0, 1,  2,  3,  0,  1,  2,  3,  0,  1,  2,  3,  0,  1,  2,  3,  0, 1, 2, 3};
		 int anchoBoton[] = {1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1, 1, 1};
		  int altoBoton[] = {1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1, 1, 1};
		
		for (int i = 0; i < totalBtn; i++){
			 botones[i] = new JButton(textoBotones[i]);
			 metodoMagico(botones[i],xBotones[i],yBotones[i],anchoBoton[i],altoBoton[i]);
			 estiloBtn(botones[i]);
			}
     }
	private void eventosNumeros() {
        for (int i = 0; i < 10; i++){
            int numBoton = numerosBotones[i];
            botones[numBoton].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (nuevoNumero){
                        if (!textoBotones[numBoton].equals("0")){
                            txtResultado.setText(textoBotones[numBoton]);
                            nuevoNumero = false; //Ya no es un nuevo n�mero
                        }
                    }
                    else{
                    	txtResultado.setText(txtResultado.getText() + textoBotones[numBoton]);
                    }
                }
            });
        }
    }
	
public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			new Ventana();
		}
	});
	}
}