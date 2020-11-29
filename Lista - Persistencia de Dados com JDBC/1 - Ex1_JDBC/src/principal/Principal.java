 
package principal;

import view.JanelaListagem;
public class Principal {
    
    public static void main(String args[]) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new JanelaListagem();
            }
        });
    }
}