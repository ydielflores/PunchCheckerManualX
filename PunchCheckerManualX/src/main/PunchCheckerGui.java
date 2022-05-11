package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import fileManager.FileManager;

public class PunchCheckerGui {

	
	private JFrame frame;
	private JTextField csvField;
	private JTextField rootField;
	private JLabel warningLabel;
	protected ImageIcon punchICO = new ImageIcon("rsc/punch.png");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PunchCheckerGui window = new PunchCheckerGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PunchCheckerGui() {
		initialize();
	}
	private void csvButton() {
		setWarningLabel("");
		JFileChooser  fileChooser = new JFileChooser("user.downloads");
		FileNameExtensionFilter etensionFilter = new FileNameExtensionFilter("CSV","csv");
		fileChooser.setFileFilter(etensionFilter);
		
		if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			csvField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}else {
			setWarningLabel("Seleccione un archivo CSV.");
		}
		
	}
	private void rootButton() {
		setWarningLabel("");
		JFileChooser  rootChooser = new JFileChooser("user.home");
		rootChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		rootChooser.setAcceptAllFileFilterUsed(false);
		
		if(rootChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			rootField.setText(rootChooser.getSelectedFile().getAbsolutePath());
		}else {
			setWarningLabel("Seleccione un folder ROOT.");
		}
		
	}
	
	private void depurarButton() {
		setWarningLabel("Depurando documento");
		FileManager fm = new FileManager(csvField.getText(), rootField.getText());
		fm.startProcess();
		setWarningLabel("Finalizado.");
	}
	
	private void setWarningLabel(String msg) {
		warningLabel.setText(msg);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 436, 245);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(punchICO.getImage());
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
		);
		
		JLabel csvLabel = new JLabel("Seleccione CSV");
		csvLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		csvField = new JTextField();
		csvField.setColumns(10);
		
		JLabel folderLabel = new JLabel("Seleccione el directorio ROOT");
		folderLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		rootField = new JTextField();
		rootField.setColumns(10);
		
		JButton csvButton = new JButton("CSV");
		csvButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		csvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csvButton();
			}
		});
		
		JButton rootButton = new JButton("ROOT");
		rootButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		rootButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rootButton();
			}
		});
		
		JButton depurarButton = new JButton("Depurar");
		depurarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depurarButton();
			}
		});
		depurarButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		warningLabel = new JLabel("");
		warningLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(warningLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(csvField, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(csvLabel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(rootField)
						.addComponent(folderLabel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(depurarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rootButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(csvButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(csvLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(csvField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(csvButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(folderLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rootField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rootButton))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(depurarButton)
						.addComponent(warningLabel))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
