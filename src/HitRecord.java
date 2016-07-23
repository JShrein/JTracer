public class HitRecord
{
	public HitRecord() 
	{ 
		t = 0;
		p = new Vec3(0,0,0);
		normal = new Vec3(0,0,0);
	}
	
	float t;
	Vec3 p;
	Vec3 normal;
}