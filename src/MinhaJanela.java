import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinhaJanela {

    JFrame janela;

    int clicks = 0;
	

    MinhaJanela(String[] args) {
        // Invoked on the event dispatching thread.
        // Do any initialization here.
	this.janela = new JFrame("Minha Janela");
	
	janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	janela.setLayout(new FlowLayout());
	//janela.setSize(100,100);

	var botão = new JButton("Me clica!");

	var coisa = new JLabel(String.valueOf(clicks));

	janela.add(coisa);
	janela.add(botão);

	botão.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clicks++;
			coisa.setText(String.valueOf(clicks));
		}
	});


	janela.pack();

    }

    public void show() {
        // Show the UI.
	janela.setVisible(true);
    }

    public static void main(final String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MinhaJanela(args).show();
            }
        });
    }
}
