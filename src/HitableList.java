import java.util.ArrayList;
import java.util.List;

public class HitableList extends Hitable
{
	public HitableList() { list = new ArrayList<Hitable>(); }
	public HitableList(List<Hitable> list) { this.list = list; }
	List<Hitable> list;

	@Override
	public boolean hit(Ray r, float t_min, float t_max, HitRecord rec) {
		HitRecord tempRec = new HitRecord();
		boolean hitAnything = false;
		float closestSoFar = t_max;
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).hit(r, t_min, closestSoFar, tempRec))
			{
				hitAnything = true;
				closestSoFar = tempRec.t;
				//rec = tempRec;  // so annoying simple assignment doesnt work here
				rec.t = tempRec.t;  
				rec.normal = tempRec.normal;
				rec.p = tempRec.p;
			}
		}
		return hitAnything;
	}

}
