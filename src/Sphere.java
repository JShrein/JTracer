
public class Sphere extends Hitable
{
	public Sphere() { }
	public Sphere(Vec3 center, float radius) { this.center = center; this.radius = radius; }
	Vec3 center;
	float radius;
	
	
	@Override
	public boolean hit(Ray r, float t_min, float t_max, HitRecord rec) {
		Vec3 oc = r.origin().Subtract(center);
		float a = r.direction().dot(r.direction());
		float b = oc.dot(r.direction());
		float c = oc.dot(oc) - radius*radius;
		float discriminant = b*b - a*c;
		if(discriminant > 0)
		{
			float discFactor = (float)Math.sqrt(discriminant);
			float temp = (-b - discFactor) / a;
			if(temp < t_max && temp > t_min)
			{
				rec.t = temp;
				rec.p = r.point_at_parameter(rec.t);
				rec.normal = (rec.p.Subtract(center)).Scale(1.0f/radius);
				return true;
			}
			temp = (-b + discFactor) / a;
			if(temp < t_max && temp > t_min)
			{
				rec.t = temp;
				rec.p = r.point_at_parameter(rec.t);
				rec.normal = (rec.p.Subtract(center)).Scale(1.0f/radius);
				return true;
			}
		}
		return false;
	}

}
