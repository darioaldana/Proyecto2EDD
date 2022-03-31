/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.edd;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
       	private JPanel contentPane;
        public Arbol tree;
	public Diagrama drawer;
         
	
	/**
	 * Create the frame.
	 */
	public GUI(Arbol tree) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 900);
                
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		drawer = new Diagrama(tree);
		
		contentPane.add(drawer);
                this.setLayout(new BorderLayout());
               	setContentPane(contentPane);
		this.tree = tree;
		setVisible(true);
	}

}



