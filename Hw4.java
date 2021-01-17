import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;


class Hw4_Label extends JLabel{
	String text;
	int s;
	String short_text;
	Dimension di;
	Hw4_Label(String s){
		super(s);
		this.setFont(null);
		setOpaque(false); 
		setHorizontalAlignment(JLabel.RIGHT);
		
	}
	public void paintComponent(Graphics g) {
		 
        int width = getWidth();   
        int height = getHeight(); 
        text=short_text=getText();
        int l = text.length();
        int sh;


        if(l>5) {
        	sh=Integer.parseInt(text);
        	if(sh<0) sh=-sh;
        	sh=sh%((int)Math.pow(10,5));
        	short_text=Integer.toString(sh);
        }

        setText(short_text);
        
        GradientPaint paint = new GradientPaint(width/2, height/2, new Color(239,204,255), width/2, height,
                new Color(204,204,255), true);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);   
        Paint oldPaint = g2d.getPaint();
        g2d.setPaint(paint);
        g2d.fillRect(0,0, width, height);
        g2d.setPaint(oldPaint);
       
        g2d.setFont(getFont());
        g2d.setColor(Color.white);
        Dimension d= this.getSize();
        
        if(((di.height-37)/3*2)>(di.width-15)) {
        	if(Integer.parseInt(getText())>=0) {
		        g2d.drawString(getText(), (d.width)*(float)0.79-(l-1)*(s*(float)0.58), (d.height)*(float)0.67);
		     }
		     else {
		       	g2d.drawString(getText(), (d.width)*(float)0.79-(l-1)*(s*(float)0.58)-(d.width)*(float)0.06, (d.height)*(float)0.67);
		     }
		}
	
		else {
			if(Integer.parseInt(getText())>=0) {
		        g2d.drawString(getText(), (d.width)*(float)0.79-(l-1)*(s*(float)0.63), (d.height)*(float)0.67);
		     }
		     else {
		       	g2d.drawString(getText(), (d.width)*(float)0.79-(l-1)*(s*(float)0.63)-(d.width)*(float)0.06, (d.height)*(float)0.67);
		     }
		}
      
        
        super.paintComponent(g);
        
        
	}
	

}
class Hw4_Button extends JButton{
	
	Hw4_Button(String s){
		super(s);   
	    setContentAreaFilled(false);

	}
	public void paintComponent(Graphics g) {
		 
			Dimension d = getSize();
	        int width = d.width;  
	        int height = d.height;	        

	        GradientPaint paint = new GradientPaint(0, 0, new Color(245,168,207), width, height,
	                new Color(221,177,236), true);
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);   
	        Paint oldPaint = g2d.getPaint();
	        g2d.setPaint(paint);
	       
	        g2d.fillRoundRect(width/18,height/10,width-width/9,height-height/5,width/5,width/5);
	        g2d.setPaint(oldPaint);
	        g2d.setColor(Color.white);
	        g2d.drawString(getText(), (float)(width)/(float)2.5, (float)(height)/(float)1.6);
	        super.paintComponent(g);

	}
}


class Hw4_ButtonPanel extends JPanel{
	ArrayList<String> num;
	Integer result;
	
	JButton []button;
	
	Hw4_Label screen;
	String opcode;
	Integer left;
	Boolean flag;
	Boolean flag2;
	
	Font font;
	
	Hw4_ButtonPanel(Hw4 p){
		setLayout(new GridLayout(4,4,0,1));
		setBackground(new Color(127,115,140));
		button=new JButton[16];
		result=0;
		left=0;
		flag=false;
		flag2=false;
		
		
		
		Hw4_ButtonEventListener listener= new Hw4_ButtonEventListener();
		
		String []op = {"7","8","9","C","4","5","6","+","1","2","3","-","0","","","="}; 
		for(int i =0; i< 16; i++) {
			button[i]=new Hw4_Button(op[i]);
			button[i].addActionListener(listener);
			if(i==3) button[i].setForeground(new Color(255,0,128));
			else button[i].setForeground(new Color(191,0,255));
			add(button[i]);
		}
		screen=p.result;
	}
	
	
	private class Hw4_ButtonEventListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton but = (JButton)e.getSource();
			if(but==Hw4_ButtonPanel.this.button[0] || but==Hw4_ButtonPanel.this.button[1]
					|| but==Hw4_ButtonPanel.this.button[2] || but==Hw4_ButtonPanel.this.button[4]
					|| but==Hw4_ButtonPanel.this.button[5] || but==Hw4_ButtonPanel.this.button[6]
					|| but==Hw4_ButtonPanel.this.button[8] || but==Hw4_ButtonPanel.this.button[9]
					|| but==Hw4_ButtonPanel.this.button[10] || but==Hw4_ButtonPanel.this.button[12]){
				if(flag2==true) {
					result=0;
					flag2=false;
				}
				result=result*10+Integer.parseInt(but.getText());
				screen.setText(Integer.toString(result));
			}
			else if(but==Hw4_ButtonPanel.this.button[3]) {
				result=0;
				left=0;
				flag=false;
				screen.setText(Integer.toString(result));
			}
			else if(but==Hw4_ButtonPanel.this.button[7]) {
				if(flag==true) {
					if(opcode.contentEquals("+")) {
						result=result+left;
					}
					else if(opcode.contentEquals("-")) {
						result=left-result;
					}
				}
				opcode="+";
				left=result;
				flag=true;
				result=0;
			}
			else if(but==Hw4_ButtonPanel.this.button[11]) {
				if(flag==true) {
					if(opcode.contentEquals("+")) {
						result=result+left;
					}
					else if(opcode.contentEquals("-")) {
						result=left-result;
					}
				}
				opcode="-";
				left=result;
				flag=true;
				result=0;
			}
			else if(but==Hw4_ButtonPanel.this.button[15]) {
				if(flag==true) {
					if(opcode.contentEquals("+")) {
						result=result+left;
					}
					else if(opcode.contentEquals("-")) {
						result=left-result;
					}
				}
				screen.setText(Integer.toString(result));
				left=0;
				flag=false;
				flag2=true;
			}
		}
	}

}

public class Hw4 extends JFrame implements ComponentListener{
	
	int w;
	int h;
	Hw4_Label result;
	Hw4_ButtonPanel bp;
	
	public static void main(String[] args) {
		new Hw4();
	}
	
	Hw4(){
		setSize(500,500);
		setTitle("Homework4");
		setFocusable(true);
		requestFocus();
		this.setMinimumSize(new Dimension(200,320));
		getContentPane().setBackground(Color.black);
		
	
		
		addComponentListener(this);
		
		setLayout(null);
		
		
		result=new Hw4_Label("0");
		result.setForeground(new Color(153,0,115));
		bp=new Hw4_ButtonPanel(this);
		

		add(result);
		add(bp);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension size = this.getSize();
		
		CompoundBorder bb;
		w = size.width;
		h = size.height;
		result.di=size;
		if(((h-37)/3*2)>(w-15)) {
			LineBorder lb1=new LineBorder(new Color(191,175,207),w*3/6/12);
			LineBorder lb2=new LineBorder(new Color(137,108,147),w*3/6/44);
			bb=new CompoundBorder(lb2,lb1);
			result.setBounds(0,(h-37)/3-(w-15)/2,(w-15),(w-15)/2);
			bp.setBounds(0,(h-37)/3,(w-15),(w-15));
			result.setFont(new Font("±Ã¼­",Font.PLAIN,w*3/12));
			result.s=w*3/12;
			result.setBorder(bb);
			for(int i=0;i<16;i++)
				bp.button[i].setFont(new Font("±Ã¼­",Font.BOLD,(w-15)/12));
		}
	
		else {
			LineBorder lb1=new LineBorder(new Color(191,175,207),h/3/12);
			LineBorder lb2=new LineBorder(new Color(137,108,147),(h-37)/3/44);
			bb=new CompoundBorder(lb2,lb1);
			result.setBounds(((w+9)-h/3*2)/2,0,(h-37)/3*2,(h-37)/3);
			
			bp.setBounds(((w+9)-h/3*2)/2,(h-37)/3,(h-37)/3*2,(h-37)/3*2);
			result.setFont(new Font("±Ã¼­",Font.BOLD,h/6));
			result.s=h/6;
			result.setBorder(bb);
			for(int i=0;i<16;i++) {
				bp.button[i].setFont(new Font("±Ã¼­",Font.BOLD,(h-37)/3*2/12));
			}
		}
		bp.revalidate();
		
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {	}

	@Override
	public void componentShown(ComponentEvent e) {	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	

}