package com.motorola.personalize.util;

import android.graphics.Color;
import android.util.Log;

public class HslColor {
    private float _hueValue = 0.0f;
    private float _lumValue = 0.0f;
    private float _satValue = 0.0f;

    private float CheckValue(float f) {
        if (f > 1.0f) {
            return 1.0f;
        }
        if (f > 0.0f) {
            return f;
        }
        return 0.0f;
    }

    private static float RGBFromHue(float f, float f2, float f3) {
        float f4;
        if (f3 < 0.0f) {
            f3 += 360.0f;
        }
        if (f3 >= 360.0f) {
            f3 -= 360.0f;
        }
        if (f3 < 60.0f) {
            f4 = (f2 - f) * f3;
        } else if (f3 < 180.0f) {
            return f2;
        } else {
            if (f3 >= 240.0f) {
                return f;
            }
            f4 = (f2 - f) * (240.0f - f3);
        }
        return f + (f4 / 60.0f);
    }

    public float getHueValue() {
        return this._hueValue;
    }

    public void setHueValue(float f) {
        this._hueValue = CheckValue(f);
    }

    public float getSatValue() {
        return this._satValue;
    }

    public void setSatValue(float f) {
        this._satValue = CheckValue(f);
    }

    public float getLumValue() {
        return this._lumValue;
    }

    public void setLumValue(float f) {
        this._lumValue = CheckValue(f);
    }

    public static HslColor CovertFromRGB(int i) {
        HslColor hslColor = new HslColor();
        Log.d("", "CovertFromRGB: ");
        hslColor.rgbToHsl(i);
        return hslColor;
    }

    public void rgbToHsl(int i) {
        int red = Color.red(i);
        int blue = Color.blue(i);
        float f = ((float) red) / 255.0f;
        float green = ((float) Color.green(i)) / 255.0f;
        float f2 = ((float) blue) / 255.0f;
        float max = Math.max(f, Math.max(green, f2));
        float min = Math.min(f, Math.min(green, f2));
        float f3 = min + max;
        float f4 = f3 / 2.0f;
        float f5 = max - min;
        if (((double) f5) == 0.0d) {
            this._hueValue = 0.0f;
            this._satValue = 0.0f;
            this._lumValue = f4;
            return;
        }
        if (f4 > 0.5f) {
            f3 = (2.0f - max) - min;
        }
        float f6 = f5 / f3;
        this._hueValue = ((f == max ? green == min ? ((max - f2) / f5) + 5.0f : 1.0f - ((max - green) / f5) : green == max ? f2 == min ? ((max - f) / f5) + 1.0f : 3.0f - ((max - f2) / f5) : f == min ? ((max - green) / f5) + 3.0f : 5.0f - ((max - f) / f5)) / 6.0f) * 360.0f;
        this._satValue = f6;
        this._lumValue = f4;
    }

    public static int HSLToRGB(float f, float f2, float f3) {
        float f4;
        float f5;
        float f6 = 255.0f;
        float f7 = f3 * 255.0f;
        float f8 = f2 * 255.0f;
        float f9 = 0.0f;
        if (f8 == 0.0f) {
            f4 = f7;
            f5 = f4;
        } else {
            float f10 = f7 < 128.0f ? ((f8 + 256.0f) * f7) / 256.0f : (f7 + f8) - ((f8 * f7) / 256.0f);
            if (f10 > 255.0f) {
                f10 = (float) Math.round(f10);
            }
            if (f10 > 254.0f) {
                f10 = 255.0f;
            }
            float f11 = (f7 * 2.0f) - f10;
            float RGBFromHue = RGBFromHue(f11, f10, f + 120.0f);
            f5 = RGBFromHue(f11, f10, f);
            f4 = RGBFromHue(f11, f10, f - 120.0f);
            f7 = RGBFromHue;
        }
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        if (f7 > 255.0f) {
            f7 = 255.0f;
        }
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > 255.0f) {
            f5 = 255.0f;
        }
        if (f4 >= 0.0f) {
            f9 = f4;
        }
        if (f9 <= 255.0f) {
            f6 = f9;
        }
        return Color.rgb(Math.round(f7), Math.round(f5), Math.round(f6));
    }
}
