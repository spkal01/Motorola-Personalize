package com.motorola.styles;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class XmlUtils {
    private XmlUtils() {
    }

    public static String throwIfInvalidAttributeValue(XmlPullParser xmlPullParser, String str) throws XmlPullParserException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue != null && !attributeValue.trim().isEmpty()) {
            return attributeValue;
        }
        throw new XmlPullParserException("invalid " + str + " attribute", xmlPullParser, (Throwable) null);
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static void beginDocument(org.xmlpull.v1.XmlPullParser r3, java.lang.String r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        L_0x0000:
            int r0 = r3.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r2 = 1
            if (r0 == r2) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            if (r0 != r1) goto L_0x003f
            java.lang.String r0 = r3.getName()
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0018
            return
        L_0x0018:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected start tag: found "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r1 = ", expected "
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        L_0x003f:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.XmlUtils.beginDocument(org.xmlpull.v1.XmlPullParser, java.lang.String):void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static void nextElement(org.xmlpull.v1.XmlPullParser r2) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        L_0x0000:
            int r0 = r2.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r1 = 1
            if (r0 == r1) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.XmlUtils.nextElement(org.xmlpull.v1.XmlPullParser):void");
    }

    public static void skipCurrentTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }
}
