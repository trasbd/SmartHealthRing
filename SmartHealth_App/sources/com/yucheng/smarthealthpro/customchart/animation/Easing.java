package com.yucheng.smarthealthpro.customchart.animation;

import android.animation.TimeInterpolator;

/* loaded from: classes4.dex */
public class Easing {
    private static final float DOUBLE_PI = 6.2831855f;
    public static final EasingFunction Linear = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.1
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return input;
        }
    };
    public static final EasingFunction EaseInQuad = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.2
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return input * input;
        }
    };
    public static final EasingFunction EaseOutQuad = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.3
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (-input) * (input - 2.0f);
        }
    };
    public static final EasingFunction EaseInOutQuad = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.4
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float f2 = input * 2.0f;
            if (f2 < 1.0f) {
                return 0.5f * f2 * f2;
            }
            float f3 = f2 - 1.0f;
            return ((f3 * (f3 - 2.0f)) - 1.0f) * (-0.5f);
        }
    };
    public static final EasingFunction EaseInCubic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.5
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (float) Math.pow(input, 3.0d);
        }
    };
    public static final EasingFunction EaseOutCubic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.6
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return ((float) Math.pow(input - 1.0f, 3.0d)) + 1.0f;
        }
    };
    public static final EasingFunction EaseInOutCubic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.7
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float fPow;
            float f2 = input * 2.0f;
            if (f2 < 1.0f) {
                fPow = (float) Math.pow(f2, 3.0d);
            } else {
                fPow = ((float) Math.pow(f2 - 2.0f, 3.0d)) + 2.0f;
            }
            return fPow * 0.5f;
        }
    };
    public static final EasingFunction EaseInQuart = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.8
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (float) Math.pow(input, 4.0d);
        }
    };
    public static final EasingFunction EaseOutQuart = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.9
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return -(((float) Math.pow(input - 1.0f, 4.0d)) - 1.0f);
        }
    };
    public static final EasingFunction EaseInOutQuart = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.10
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float fPow;
            float f2;
            float f3 = input * 2.0f;
            if (f3 < 1.0f) {
                fPow = (float) Math.pow(f3, 4.0d);
                f2 = 0.5f;
            } else {
                fPow = ((float) Math.pow(f3 - 2.0f, 4.0d)) - 2.0f;
                f2 = -0.5f;
            }
            return fPow * f2;
        }
    };
    public static final EasingFunction EaseInSine = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.11
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (-((float) Math.cos(input * 1.5707963267948966d))) + 1.0f;
        }
    };
    public static final EasingFunction EaseOutSine = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.12
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (float) Math.sin(input * 1.5707963267948966d);
        }
    };
    public static final EasingFunction EaseInOutSine = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.13
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return (((float) Math.cos(input * 3.141592653589793d)) - 1.0f) * (-0.5f);
        }
    };
    public static final EasingFunction EaseInExpo = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.14
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input == 0.0f) {
                return 0.0f;
            }
            return (float) Math.pow(2.0d, (input - 1.0f) * 10.0f);
        }
    };
    public static final EasingFunction EaseOutExpo = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.15
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input == 1.0f) {
                return 1.0f;
            }
            return -((float) Math.pow(2.0d, (input + 1.0f) * (-10.0f)));
        }
    };
    public static final EasingFunction EaseInOutExpo = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.16
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float fPow;
            if (input == 0.0f) {
                return 0.0f;
            }
            if (input == 1.0f) {
                return 1.0f;
            }
            if (input * 2.0f < 1.0f) {
                fPow = (float) Math.pow(2.0d, (r9 - 1.0f) * 10.0f);
            } else {
                fPow = (-((float) Math.pow(2.0d, (r9 - 1.0f) * (-10.0f)))) + 2.0f;
            }
            return fPow * 0.5f;
        }
    };
    public static final EasingFunction EaseInCirc = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.17
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return -(((float) Math.sqrt(1.0f - (input * input))) - 1.0f);
        }
    };
    public static final EasingFunction EaseOutCirc = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.18
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float f2 = input - 1.0f;
            return (float) Math.sqrt(1.0f - (f2 * f2));
        }
    };
    public static final EasingFunction EaseInOutCirc = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.19
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float fSqrt;
            float f2;
            float f3 = input * 2.0f;
            if (f3 < 1.0f) {
                fSqrt = ((float) Math.sqrt(1.0f - (f3 * f3))) - 1.0f;
                f2 = -0.5f;
            } else {
                float f4 = f3 - 2.0f;
                fSqrt = ((float) Math.sqrt(1.0f - (f4 * f4))) + 1.0f;
                f2 = 0.5f;
            }
            return fSqrt * f2;
        }
    };
    public static final EasingFunction EaseInElastic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.20
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input == 0.0f) {
                return 0.0f;
            }
            if (input == 1.0f) {
                return 1.0f;
            }
            float f2 = input - 1.0f;
            return -(((float) Math.pow(2.0d, 10.0f * f2)) * ((float) Math.sin(((f2 - (0.047746483f * ((float) Math.asin(1.0d)))) * Easing.DOUBLE_PI) / 0.3f)));
        }
    };
    public static final EasingFunction EaseOutElastic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.21
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input == 0.0f) {
                return 0.0f;
            }
            if (input == 1.0f) {
                return 1.0f;
            }
            return (((float) Math.pow(2.0d, (-10.0f) * input)) * ((float) Math.sin(((input - (0.047746483f * ((float) Math.asin(1.0d)))) * Easing.DOUBLE_PI) / 0.3f))) + 1.0f;
        }
    };
    public static final EasingFunction EaseInOutElastic = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.22
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input == 0.0f) {
                return 0.0f;
            }
            float f2 = input * 2.0f;
            if (f2 == 2.0f) {
                return 1.0f;
            }
            float fAsin = ((float) Math.asin(1.0d)) * 0.07161972f;
            if (f2 < 1.0f) {
                float f3 = f2 - 1.0f;
                return ((float) Math.pow(2.0d, 10.0f * f3)) * ((float) Math.sin(((f3 * 1.0f) - fAsin) * Easing.DOUBLE_PI * 2.2222223f)) * (-0.5f);
            }
            float f4 = f2 - 1.0f;
            return (((float) Math.pow(2.0d, (-10.0f) * f4)) * 0.5f * ((float) Math.sin(((f4 * 1.0f) - fAsin) * Easing.DOUBLE_PI * 2.2222223f))) + 1.0f;
        }
    };
    public static final EasingFunction EaseInBack = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.23
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return input * input * ((input * 2.70158f) - 1.70158f);
        }
    };
    public static final EasingFunction EaseOutBack = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.24
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float f2 = input - 1.0f;
            return (f2 * f2 * ((f2 * 2.70158f) + 1.70158f)) + 1.0f;
        }
    };
    public static final EasingFunction EaseInOutBack = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.25
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            float f2 = input * 2.0f;
            if (f2 < 1.0f) {
                return f2 * f2 * ((3.5949094f * f2) - 2.5949094f) * 0.5f;
            }
            float f3 = f2 - 2.0f;
            return ((f3 * f3 * ((3.5949094f * f3) + 2.5949094f)) + 2.0f) * 0.5f;
        }
    };
    public static final EasingFunction EaseInBounce = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.26
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            return 1.0f - Easing.EaseOutBounce.getInterpolation(1.0f - input);
        }
    };
    public static final EasingFunction EaseOutBounce = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.27
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input < 0.36363637f) {
                return 7.5625f * input * input;
            }
            if (input < 0.72727275f) {
                float f2 = input - 0.54545456f;
                return (7.5625f * f2 * f2) + 0.75f;
            }
            if (input < 0.90909094f) {
                float f3 = input - 0.8181818f;
                return (7.5625f * f3 * f3) + 0.9375f;
            }
            float f4 = input - 0.95454544f;
            return (7.5625f * f4 * f4) + 0.984375f;
        }
    };
    public static final EasingFunction EaseInOutBounce = new EasingFunction() { // from class: com.yucheng.smarthealthpro.customchart.animation.Easing.28
        @Override // com.yucheng.smarthealthpro.customchart.animation.Easing.EasingFunction, android.animation.TimeInterpolator
        public float getInterpolation(float input) {
            if (input < 0.5f) {
                return Easing.EaseInBounce.getInterpolation(input * 2.0f) * 0.5f;
            }
            return (Easing.EaseOutBounce.getInterpolation((input * 2.0f) - 1.0f) * 0.5f) + 0.5f;
        }
    };

    public interface EasingFunction extends TimeInterpolator {
        @Override // android.animation.TimeInterpolator
        float getInterpolation(float input);
    }
}
