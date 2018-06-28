package animalsDecorator;

import graphics.ZooPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import animals.Animal;

public class DecorateDialog extends JDialog implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;

	private JPanel p1,p2;
	private TitledBorder border,border2;
	private JButton ok;
	private ButtonGroup rbg;
	private JRadioButton redBut,blueBut;
	private JComboBox<String> box;
	private String chosenColor;
	private ArrayList<Animal> animalsToDecorate;
	private int numOfAnimal;
	
	
	public DecorateDialog(String title,ZooPanel pan)
	{

		super(new JFrame(),title,true);

		setSize(600,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(new Color(1,230,255));

		animalsToDecorate= new ArrayList<Animal>();

		buildPanels(pan);

		setVisible(true);

	}
	/*
	 * build the panel
	 */
	public void buildPanels(ZooPanel pan)
	{
		border = new TitledBorder("Select Animal to decorate");
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);
		border.setTitleFont(new Font("Arial", Font.BOLD, 18));

		border2 = new TitledBorder("Choose decoration color");
		border2.setTitleJustification(TitledBorder.RIGHT);
		border2.setTitlePosition(TitledBorder.TOP);
		border2.setTitleFont(new Font("Arial", Font.BOLD, 18));

		p1 = new JPanel();
		p1.setBorder(border);
		p1.setSize(300, 150);


		p2 = new JPanel();
		p2.setBorder(border2);
		p2.setLayout(new GridLayout(4,2,35,35));


		rbg = new ButtonGroup();
		redBut=new JRadioButton("Red",false);
		blueBut=new JRadioButton("Blue",false);
		redBut.addItemListener(this);
		blueBut.addItemListener(this);

		rbg.add(redBut);
		rbg.add(blueBut);
		p2.add(redBut);
		p2.add(blueBut);

		box = new JComboBox<>();
		box.addItem("No animal");

		int i=1;
		for(Animal an : pan.getAnimals() )
		{
			if(an.getColor().equals("Natural"))
			{
				box.addItem(i+".["+an.getName()+": running = "+an.isRuning()+
						",weight = "+an.getWeight()+",color = "+an.getColor());
				i++;
				animalsToDecorate.add(an);
			}
		}

		box.addActionListener(this);

		ok=new JButton("OK");
		ok.setBackground(Color.lightGray);
		ok.addActionListener(this);

		p1.setLayout(new BorderLayout());

		p1.add(ok,BorderLayout.PAGE_END);
		p1.add(box,BorderLayout.PAGE_START);

		setLayout(new GridLayout(1, 2));
		add(p1);
		add(p2);

	}


	public void itemStateChanged(ItemEvent e)
	{
		if(redBut.isSelected())
			chosenColor="Red";
		else if(blueBut.isSelected())
			chosenColor="Blue";
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == box)
		{
			String chosenAnimal = (String) box.getSelectedItem();
			if(!chosenAnimal.equals("No animal"))
				numOfAnimal = Character.getNumericValue(chosenAnimal.charAt(0));
		}

		if(e.getSource() == ok)
		{
			if(numOfAnimal > 0)
			{
				Animal an = animalsToDecorate.get(numOfAnimal - 1);
				ColoredAnimalDecorator dec = new ColoredAnimalDecorator(an);
				dec.PaintAnimal(chosenColor);
			}
			setVisible(false);
		}
	}

}
