import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Component;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class SuccessfulRegistration extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the dialog.
	 */
	public SuccessfulRegistration(Kursant kursant) {
		setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(false);
		
		
		Component horizontalStrut = Box.createHorizontalStrut(80);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(80);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		JLabel lblRejestracjaPrezbiegaPomylnie = new JLabel("Rejestracja przebiegła pomyślnie");
		lblRejestracjaPrezbiegaPomylnie.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRejestracjaPrezbiegaPomylnie.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblRejestracjaPrezbiegaPomylnie, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.controlHighlight);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnOk, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		CardLayout cardL=new CardLayout(0,0);
		panel_1.setLayout(cardL);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "name_1762574521527300");
		panel_2.setLayout(new GridLayout(10, 3, 0, 0));
		
		JLabel lblDaneLogowania = new JLabel("Dane logowania");
		lblDaneLogowania.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblDaneLogowania);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_3);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblLogin);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_5);
		
		JLabel lblHaso = new JLabel("Hasło:");
		lblHaso.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblHaso);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "name_1762580625159000");
		panel_3.setLayout(new GridLayout(10, 3, 0, 0));
		
		JLabel lblDaneLogowania_1 = new JLabel("Dane logowania");
		lblDaneLogowania_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblDaneLogowania_1);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_4);
		
		JLabel lblDlaOpiekuna = new JLabel("Dla kursanta:");
		panel_3.add(lblDlaOpiekuna);
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_3.add(horizontalBox);
		
		JLabel lblLogin_1 = new JLabel("Login:");
		horizontalBox.add(lblLogin_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		horizontalBox.add(textField_2);
		textField_2.setColumns(10);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_3.add(horizontalBox_1);
		
		JLabel lblHaso_1 = new JLabel("Hasło:");
		horizontalBox_1.add(lblHaso_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		horizontalBox_1.add(textField_3);
		textField_3.setColumns(10);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_7);
		
		JLabel lblDlaOpiekuna_1 = new JLabel("Dla opiekuna:");
		panel_3.add(lblDlaOpiekuna_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panel_3.add(horizontalBox_2);
		
		JLabel lblLogin_2 = new JLabel("Login:");
		horizontalBox_2.add(lblLogin_2);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		horizontalBox_2.add(textField_4);
		textField_4.setColumns(10);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panel_3.add(horizontalBox_3);
		
		JLabel lblHaso_2 = new JLabel("Hasło:");
		horizontalBox_3.add(lblHaso_2);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		horizontalBox_3.add(textField_5);
		textField_5.setColumns(10);

		if(kursant.getOpiekun()==null) {
			cardL.show(panel_1, "name_1762574521527300");
			textField.setText(kursant.getLogin());
			textField_1.setText(kursant.getHaslo());
		} else {
			cardL.show(panel_1, "name_1762580625159000");
			textField_2.setText(kursant.getLogin());
			textField_3.setText(kursant.getHaslo());
			
			textField_4.setText(kursant.getOpiekun().getLogin());
			textField_5.setText(kursant.getOpiekun().getHaslo());
		}
	}

}
