import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class TextEditor extends JFrame implements ActionListener {
 
JLabel fontLabel;
JButton fontColorButton;
JComboBox fontBox;
JTextArea textArea;
JSpinner fontSizeSpinner;
JScrollPane scrollPane;

JMenuBar menuBar;  
JMenu menu;
JMenuItem openFile;
JMenuItem saveFile;
JMenuItem exit;

TextEditor(){
this.setSize(500,500);
this.setTitle("Text Editor");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setLayout(new FlowLayout());
this.setLocationRelativeTo(null);

textArea = new JTextArea();
textArea.setLineWrap(true);
textArea.setWrapStyleWord(true);
textArea.setFont(new Font("Arial",Font.PLAIN,20));

scrollPane = new JScrollPane(textArea);
scrollPane.setPreferredSize(new Dimension(450,450));
scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

fontLabel = new JLabel("Font: ");
fontSizeSpinner = new JSpinner();
fontSizeSpinner.setPreferredSize(new Dimension(50,25));
fontSizeSpinner.setValue(20);
fontSizeSpinner.addChangeListener(new ChangeListener() {

	@Override
	public void stateChanged(ChangeEvent e) {
		textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue()));
	}
	
});

String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
fontBox = new JComboBox(fonts);
fontBox.addActionListener(this);
fontBox.setSelectedItem("Arial");

fontColorButton = new JButton("Color");
fontColorButton.addActionListener(this);

menuBar = new JMenuBar();
menu = new JMenu("File");
saveFile = new JMenuItem("Save");
openFile = new JMenuItem("Open");
exit = new JMenuItem("Exit");

openFile.addActionListener(this);
saveFile.addActionListener(this);
exit.addActionListener(this);

menu.add(openFile);
menu.add(saveFile);
menu.add(exit);
menuBar.add(menu);

this.setJMenuBar(menuBar);
this.add(fontSizeSpinner);
this.add(fontLabel);
this.add(fontColorButton);
this.add(fontBox);
this.add(scrollPane);
this.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==fontBox) {
		textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
	}
	
	if(e.getSource()==fontColorButton) {
		JColorChooser colorChooser = new JColorChooser();
		Color color = colorChooser.showDialog(null, "Chooser a color", Color.black);
		textArea.setForeground(color);
	}
	
	if(e.getSource()==exit) {
		System.exit(0);
	}
}

}