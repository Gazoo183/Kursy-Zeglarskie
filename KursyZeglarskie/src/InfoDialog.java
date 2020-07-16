import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;

public class InfoDialog extends JDialog {
	private JLabel label;
	private JTextPane label_1;

	public InfoDialog(String title, String header, String information) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		setBounds(100, 100, 450, 300);
		setTitle(title);
		setModal(true);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("");
				label.setText("");
				label_1.setText("");
				setVisible(false);
			}
		});
		btnOk.setBackground(SystemColor.controlHighlight);
		panel.add(btnOk);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		label = new JLabel(header);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JTextPane();
		label_1.setBackground(UIManager.getColor("Panel.background"));
		label_1.setEditable(false);
		label_1.setText(information);
		panel_2.add(label_1, BorderLayout.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(101);
		panel_2.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(100);
		panel_2.add(horizontalStrut_1, BorderLayout.EAST);

	}

}
