
public class Ray 
{
	public Vec3 o;
	public Vec3 d;
	
	public Ray() { }
	public Ray(Vec3 origin, Vec3 direction) { o = origin; d = direction; }
	public Vec3 origin() { return o; }
	public Vec3 direction() { return d; }
	
	public Vec3 point_at_parameter(float t) { return o.Add(d.Scale(t)); }
}
