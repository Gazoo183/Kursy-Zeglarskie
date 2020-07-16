import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class FormNotFilled extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public FormNotFilled(Frame owner) {
		super(owner);
		setModal(true);
		setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(false);
		this.setAlwaysOnTop(true);
		setTitle("Error");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JButton okButton = new JButton("OK");
			okButton.setBackground(SystemColor.controlHighlight);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisibility(false);
				}
			});
			contentPanel.add(okButton, BorderLayout.SOUTH);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel fillMessage = new JLabel("Należy wypełnić wszystkie pola!");
			fillMessage.setForeground(Color.RED);
			fillMessage.setFont(new Font("Tahoma", Font.BOLD, 25));
			fillMessage.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(fillMessage, BorderLayout.CENTER);
		}
	}
	
	public void setVisibility(boolean bool) {
		this.setVisible(bool);
	}
}
