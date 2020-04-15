

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class DataBaseGUI {
	private static JFrame frame;
	private static JPanel panel;
	
	//LABELS
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	
	
	//BUTTONS
	private static JButton buttonQuit;
	private static JButton button1;
	private static JButton button2;
	private static JButton button3;
	private static JButton button4;
	private static JButton button5;
	
	
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		panel = new JPanel();
		
		frame.setTitle("SPA Resort App");
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		
		//BUTTON
		button1 = new JButton("Go");
		button1.setBounds(50, 40, 80, 25);
		button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked go");
            }
        });
		panel.add(button1);
		label1 = new JLabel("Show guests");
		label1.setBounds(200, 40, 100, 25);
		panel.add(label1);

		
		button2 = new JButton("Go");
		button2.setBounds(50, 100, 80, 25);
		button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked 2");
            }
        });
		panel.add(button2);
		label2 = new JLabel("Increase the salary");
		label2.setBounds(200, 100, 200, 25);
		panel.add(label2);
			
		
		button3 = new JButton("Go");
		button3.setBounds(50, 160, 80, 25);
		button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked 3");
            }
        });
		panel.add(button3);
		label3 = new JLabel("Change room status");
		label3.setBounds(200, 160, 200, 25);
		panel.add(label3);
		
		
		button4 = new JButton("Go");
		button4.setBounds(50, 220, 80, 25);
		button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked 4");
            }
        });
		panel.add(button4);
		label4 = new JLabel("Make a reservation");
		label4.setBounds(200, 220, 200, 25);
		panel.add(label4);
		
		
		button5 = new JButton("Go");
		button5.setBounds(50, 280, 80, 25);
		button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked 5");
            }
        });
		panel.add(button5);
		label5 = new JLabel("Get information");
		label5.setBounds(200, 280, 200, 25);
		panel.add(label5);
		
		
		buttonQuit = new JButton("Quit");
		buttonQuit.setBounds(50, 340, 80, 25);
		buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked Quit");
            }
        });
		panel.add(buttonQuit);
		
		
		
		
		
		
		
		
		
		frame.setVisible(true);
	}





//	@Override
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("clicked");
//		
//	}

	
}
