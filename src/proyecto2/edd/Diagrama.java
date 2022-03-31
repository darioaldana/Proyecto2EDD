/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.edd;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author dario
 */

class Diagrama extends JPanel{
	
	public Arbol tree;
	
	public Diagrama(Arbol tree){
		this.tree = tree;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	
		g.setFont(new Font("Calibri", Font.BOLD, 12));
		//g.drawString(String.valueOf(tree.root.data), this.getWidth()/2, 30);
		

			//DrawNode(g, tree.root,100, 50,2);

		Draw(g, 0, getWidth(), 0, getHeight() / tree.getHeight(tree.root), tree.root);
	}

	
    public void Draw(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Nodo node) {
        String data = String.valueOf(node.getData());
        g.setFont(new Font("Calibri", Font.BOLD, 12));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);
        g.drawString(data, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);

        if (node.getLeft() != null)            
        	Draw(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getLeft());
        
        if (node.getRight() != null)
        	Draw(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getRight());
    }
	
	
}

