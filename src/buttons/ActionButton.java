package buttons;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;


public class ActionButton extends JButton{
	private JLabel function;
	
	public ActionButton() {
		super();
		function = new JLabel();
		function.setVisible(true);
		this.setLayout(new FlowLayout());
		this.add(function, FlowLayout.CENTER);
		this.setVisible(true);
		this.validate();
	}
	
	public ActionButton(String s){
		super(s);
		function = new JLabel(s);
		function.setVisible(true);
		this.setLayout(new FlowLayout());
		this.add(function, FlowLayout.CENTER);
		this.setVisible(true);
		this.validate();
	}
	
	public JLabel getFunction() {
		return function;
	}

	public void setFunction(JLabel action) {
		this.function = action;
	}

}
