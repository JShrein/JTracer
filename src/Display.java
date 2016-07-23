import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Display extends JFrame
{
	private int width;
	private int height;
	private String title;
	
	private JPanel frame;
	private JButton traceButton;
	
	private BufferedImage tracedImage;
	
	public Display()
	{
		this(800, 600, "Ray Tracer");
	}
	
	public Display(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		
		this.title = title;
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel frame = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(tracedImage, 0, 0, this);
			}
			
		};
		frame.setSize(new Dimension(800, 600));
		
		this.add(frame);
		
		traceButton = new JButton("Trace");
		traceButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				tracedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
				int ns = 100;
				
				List<Hitable> objList = new ArrayList<Hitable>();
				objList.add(new Sphere(new Vec3(0,0,-1), 0.5f));
				objList.add(new Sphere(new Vec3(0,-100.5f,-1), 100));
				Hitable world = new HitableList(objList);
				
				Camera camera = new Camera();
				
				for(int j = tracedImage.getHeight() ; j > 0; j--)
				{
					for(int i = 0; i < tracedImage.getWidth(); i++)
					{
						Vec3 col = new Vec3(0,0,0);
						for(int s = 0; s < ns; s++)
						{
							float u = (float)(i + Math.random())/(float)tracedImage.getWidth();
							float v = (float)(j + Math.random())/(float)tracedImage.getHeight();
							Ray r = camera.GetRay(u, v);
							Vec3 p = r.point_at_parameter(2.0f);
							col = col.Add(color(r,world));
						}
						col = col.Scale(1.0f/(float)ns);
						float ir = (int)(255.99f * col.x());
						float ig = (int)(255.99f * col.y());
						float ib = (int)(255.99f * col.z());
						
						tracedImage.setRGB(i, tracedImage.getHeight() - j, TColor.GetColor(ir, ig, ib));
					}
					frame.repaint();
				}
				frame.repaint();
			}
			
		});

		traceButton.setMaximumSize(new Dimension(125, 35));
		traceButton.setPreferredSize(new Dimension(125,35));
		traceButton.setSize(new Dimension(125,35));
		traceButton.setLocation(new Point(this.width/2 - 125/2, this.height - 60));
		
		this.add(traceButton, BorderLayout.SOUTH);
	}
	
	public float hitSphere(final Vec3 center, float radius, final Ray r)
	{
		Vec3 oc = r.origin().Subtract(center);
		float a = r.direction().dot(r.direction());
		float b = 2.0f * oc.dot(r.direction());
		float c = oc.dot(oc) - radius * radius;
		float discriminant = b*b - 4*a*c;
		if (discriminant < 0)
		{
			return -1.0f;
		}
		else
		{
			return (-b - (float)Math.sqrt(discriminant)) / (2.0f * a);
		}
	}
	
	public Vec3 color(Ray r, Hitable world)
	{
		HitRecord rec = new HitRecord();
		if(world.hit(r, 0.0f, Float.MAX_VALUE, rec))
		{
			return new Vec3(rec.normal.x() + 1, rec.normal.y() + 1, rec.normal.z() + 1).Scale(0.5f);
		}
		else
		{
			Vec3 unit_dir = r.direction().normalize();
			float t = 0.5f * (unit_dir.y() + 1.0f);
			return new Vec3(1.0f, 1.0f, 1.0f).Scale(1.0f - t).Add(new Vec3(0.5f, 0.7f, 1.0f).Scale(t));			
		}
	}
}
