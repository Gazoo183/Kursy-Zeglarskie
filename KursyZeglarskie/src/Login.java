import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Create the dialog.
	 */
	
	public Login(Home home, Frame owner, CardLayout ppl, JPanel pcp, CardLayout cl) {
		super(owner);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		setResizable(false);
		setModal(true);
		setTitle("Logowanie");
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zaloguj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textField.getText().length()==0 || textField_1.getPassword().length==0)
							new FormNotFilled(owner).setVisible(true);
						else {
							try {
								if(textField.getText().contains("k")) {
									Kursant tmp=Kursant.validate(textField.getText(), new String(textField_1.getPassword()));
									if(tmp!=null) {
										ppl.show(pcp, "name_1816853342767200");
										cl.show(pcp.getParent().getParent(), "name_1820385697516000");
										home.fillKP(tmp);
										textField.setText("");
										textField_1.setText("");
										setVisible(false);
									} else {
										new FormError(owner, "niepoprawne dane logowania").setVisible(true);
									}
								} else if(textField.getText().contains("o")) {
									Opiekun tmp=Opiekun.validate(textField.getText(), new String(textField_1.getPassword()));
									if(tmp!=null) {
										ppl.show(pcp, "name_1816876853591800");
										cl.show(pcp.getParent().getParent(), "name_1820385697516000");
										home.fillOP(tmp);
										textField.setText("");
										textField_1.setText("");
										setVisible(false);
									} else {
										new FormError(owner, "niepoprawne dane logowania").setVisible(true);
									}
								} else if(textField.getText().contains("i")) {
									Instruktor tmp=Instruktor.validate(textField.getText(), new String(textField_1.getPassword()));
									if(tmp!=null) {
										ppl.show(pcp, "name_1816869836546200");
										cl.show(pcp.getParent().getParent(), "name_1820385697516000");
										home.fillIP(tmp);
										textField.setText("");
										textField_1.setText("");
										setVisible(false);
									} else {
										new FormError(owner, "niepoprawne dane logowania").setVisible(true);
									}
								} else if(textField.getText().contains("s")) {
									Instruktor tmp=Sternik.validate(textField.getText(), new String(textField_1.getPassword()));
									if(tmp!=null) {
										ppl.show(pcp, "name_1820184974968600");
										cl.show(pcp.getParent().getParent(), "name_1820385697516000");
										home.fillIP(tmp);
										textField.setText("");
										textField_1.setText("");
										setVisible(false);
									} else {
										new FormError(owner, "niepoprawne dane logowania").setVisible(true);
									}
								} else {
									new FormError(owner, "niepoprawne dane logowania").setVisible(true);
								}
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
							
							
						}
					}
				});
				okButton.setBackground(SystemColor.controlHighlight);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Anuluj");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText("");
						textField_1.setText("");
						setVisible(false);
					}
				});
				cancelButton.setBackground(SystemColor.controlHighlight);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(contentPanel, BorderLayout.CENTER);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(new GridLayout(5, 0, 0, 0));
			{
				JLabel lblZalogujSiZa = new JLabel("Zaloguj się");
				lblZalogujSiZa.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblZalogujSiZa.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(lblZalogujSiZa);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				contentPanel.add(verticalStrut);
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				horizontalBox.setBorder(new EmptyBorder(5, 5, 2, 5));
				contentPanel.add(horizontalBox);
				{
					JLabel lblLogin = new JLabel("Login: ");
					horizontalBox.add(lblLogin);
				}
				{
					textField = new JTextField();
					horizontalBox.add(textField);
					textField.setColumns(10);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				horizontalBox.setBorder(new EmptyBorder(2, 5, 5, 5));
				contentPanel.add(horizontalBox);
				{
					JLabel lblHaso = new JLabel("Hasło: ");
					horizontalBox.add(lblHaso);
				}
				{
					textField_1 = new JPasswordField();
					horizontalBox.add(textField_1);
					textField_1.setColumns(10);
				}
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(80);
				panel.add(horizontalStrut, BorderLayout.WEST);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(80);
				panel.add(horizontalStrut, BorderLayout.EAST);
			}
		}
	}

}
