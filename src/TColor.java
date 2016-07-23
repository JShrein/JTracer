
public class TColor 
{
	private static final float A = 255;

	public static int GetColor(float r, float g, float b)
	{
		int finalColor = 0;
		
		finalColor += A; // 0xFF
		finalColor = (finalColor << 8) + (int)r;	// 0xFF00 + rr
		finalColor = (finalColor << 8) + (int)g;	// 0xFFrr00 + gg
		finalColor = (finalColor << 8) + (int)b;	// 0xFFrrgg00 + bb
		
		return finalColor;
	}
	
	public static int GetColor(int r, int g, int b)
	{
		int finalColor = 0;
		
		finalColor += A; // 0xFF
		finalColor = (finalColor << 8) + r;	// 0xFF00 + rr
		finalColor = (finalColor << 8) + g;	// 0xFFrr00 + gg
		finalColor = (finalColor << 8) + b;	// 0xFFrrgg00 + bb
		
		return finalColor;
	}
}
