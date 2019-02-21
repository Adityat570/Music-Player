package com.ldt.musicr.mediadata.oldmodel;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by trung on 11/4/2017.
 */

public class CPointF extends PointF {
   public CPointF getPointAround(float distance, float degree) {
       degree = -degree + 90;
        CPointF p = new CPointF();
        p.x = (float)(x + distance*Math.cos(Math.toRadians(degree)));
        p.y = (float)( y - distance*Math.sin(Math.toRadians(degree)));
        Log.d("CPointF","touch : (x = "+ x+", y = "+y+" ), distance = " + distance);
       Log.d("CPointF" ,"degree: " +degree+", p.x = "+ p.x+", p.y = "+ p.y);

        return  p;
    }

    public float fromCPointFToDegree_From0h(CPointF point) {
       float angle = (float) getAngle(point);
       angle += 90;
       if(angle>360) angle%=360;
       if(angle > 180) angle =- (360 - angle);
       return angle;
    }
    public float fromCPointFToDegree_From0h(float s_x,float s_y) {
        float angle = (float) getAngle(s_x,s_y);
        angle += 90;
        if(angle>360) angle%=360;
        if(angle > 180) angle =- (360 - angle);
        return angle;
    }
    /**
     * Fetches angle relative to screen centre pp_point
     * where 3 O'Clock is 0 and 12 O'Clock is 270 degrees
     *
     * @param screenPoint
     * @return angle in degress from 0-360.
     */
    public double getAngle(CPointF screenPoint) {
        double dx = screenPoint.x - x;
        // Minus to correct for coord re-mapping
        double dy = -(screenPoint.y - y);

        double inRads = Math.atan2(dy, dx);

        // We need to map to coord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
        if (inRads < 0)
            inRads = Math.abs(inRads);
        else
            inRads = 2 * Math.PI - inRads;

        return Math.toDegrees(inRads);
    }
    public double getAngle(float s_x,float s_y) {
        double dx = s_x - x;
        // Minus to correct for coord re-mapping
        double dy = -(s_y - y);

        double inRads = Math.atan2(dy, dx);

        // We need to map to coord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
        if (inRads < 0)
            inRads = Math.abs(inRads);
        else
            inRads = 2 * Math.PI - inRads;

        return Math.toDegrees(inRads);
    }
    private CPointF() {

    }
    public CPointF(final CPointF c) {
        this.x = c.x;
        this.y = c.y;
    }
    public CPointF(float x, float y)   {
        this.x = x;
        this.y = y;
    }

    public CPointF(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    public double getDistance(CPointF c) {
        return Math.sqrt(Math.pow(x-c.x, 2) + Math.pow((y-c.y), 2));
    }
    public double getDistance(float s_x,float s_y) {
        return Math.sqrt(Math.pow( x - s_x, 2 ) + Math.pow( y - s_y, 2 ));
    }

    @Override
    public String toString() {
         return "("+x+", "+y+")";
    }
}