/*
Copyright (c) 2009 The Regents of the University of California.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS..
 */
package org.clothocad.hibernate.data;
// Generated Jun 1, 2010 8:47:54 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import org.clothocore.api.data.Annotation;
import org.clothocore.api.data.Annotation.AnnotationDatum;
import org.clothocore.api.data.ObjBase;
import org.clothocore.api.data.ObjBase.ObjBaseDatum;
import org.clothocore.api.data.ObjType;
import org.clothocad.hibernate.hibernateDatum;

/**
 * NucseqAnnotation generated by hbm2java
 */
public class NucseqAnnotation implements java.io.Serializable, hibernateDatum {

    public NucseqAnnotation ( Annotation a ) {
        annotationId = a.getUUID();
        name = a.getName();
        personTable = new PersonTable( a.getAuthor().getUUID() );
        if (a.getFeature() != null) {
            featureTable = new FeatureTable( a.getFeature().getUUID() );
        }
        nucseqTable = new NucseqTable( a.getSeq().getUUID() );
        forwardColor = a.getForwardColorAsInt();
        reverseColor = a.getReverseColorAsInt();
        start = a.getStart();
        end = a.getEnd();
        reverseComplement = a.isForwardStrand();
        dateCreated = a.getDateCreated();
        lastModified = a.getLastModified();
    }

    @Override
    public ObjBase getObject () {
        Annotation.AnnotationDatum d = (AnnotationDatum) getObjBaseDatum();
        Annotation ann = new Annotation( d );
        return ann;
    }

    @Override
    public void runSecondaryProcessing(ObjBase obj) {
    }

    @Override
    public boolean needsSecondaryProcessing() {
        return false;
    }

    @Override
    public ObjBaseDatum getObjBaseDatum() {
        Annotation.AnnotationDatum d = new Annotation.AnnotationDatum();
        String authorUUID = null;
        String featureUUID = null;
        String nucseqID = null;
        boolean forStrand = true;
        String uuid = annotationId;

        if(personTable != null) {
            authorUUID = personTable.getIdPerson();
        }

        if(featureTable != null) {
            featureUUID = featureTable.getIdFeature();
        }

        if(nucseqTable != null) {
            nucseqID = nucseqTable.getIdNucseq();
        }

        if(reverseComplement != null) {
            forStrand = reverseComplement;
        }

        d._authorUUID = authorUUID;
        d._featureID = featureUUID;
        d._nucseqID = nucseqID;
        d._forColor = forwardColor;
        d._revColor = reverseColor;
        d._start = start;
        d._end = end;
        d._forStrand = forStrand;
        d.uuid = uuid;
        d.name = name;
        d.dateCreated = dateCreated;
        d.lastModified = lastModified;
        return d;
    }

    @Override
    public String getUUID () {
        return annotationId;
    }

    public static String translate ( Enum field ) {
        if ( field.getDeclaringClass() != Annotation.Fields.class ) {
            return null;
        }

        Annotation.Fields f = (Annotation.Fields) field;

        switch ( f ) {
            case NAME:
                return "name";
            case DATE_CREATED:
                return "dateCreated";
            case LAST_MODIFIED:
                return "lastModified";
            case FORWARD_COLOR:
                return "forwardColor";
            case REVERSE_COLOR:
                return "reverseColor";
            case START:
                return "start";
            case END:
                return "end";
            case SYMBOL:
                return "";
            case IS_FORWARD_STRAND:
                return "reverseComplement";
            case AUTHOR:
                return "personTable";
            case FEATURE:
                return "featureTable";
            case TARGET_SEQUENCE:
                return "nucseqTable";
            default:
                return null;
        }
    }

    public static ObjType getType( Enum field ) {
        if ( field.getDeclaringClass() != Annotation.Fields.class ) {
            return null;
        }

        Annotation.Fields f = (Annotation.Fields) field;

        switch ( f ) {
            case AUTHOR:
                return ObjType.PERSON;
            case FEATURE:
                return ObjType.FEATURE;
            case TARGET_SEQUENCE:
                return ObjType.NUCSEQ;
            default:
                return null;
        }
    }
    
    /***** AUTO-GENERATED CODE *****/
    private String annotationId;
    private PersonTable personTable;
    private FeatureTable featureTable;
    private NucseqTable nucseqTable;
    private String name;
    private Integer forwardColor;
    private Integer reverseColor;
    private Integer start;
    private Integer end;
    private Boolean reverseComplement;
    private Date dateCreated;
    private Date lastModified;

    public NucseqAnnotation () {
    }

    public NucseqAnnotation ( String annotationId ) {
        this.annotationId = annotationId;
    }

    public NucseqAnnotation ( String annotationId, PersonTable personTable, FeatureTable featureTable, NucseqTable nucseqTable, String name, Integer forwardColor, Integer reverseColor, Integer start, Integer end, Boolean reverseComplement, Date dateCreated, Date lastModified ) {
        this.annotationId = annotationId;
        this.personTable = personTable;
        this.featureTable = featureTable;
        this.nucseqTable = nucseqTable;
        this.name = name;
        this.forwardColor = forwardColor;
        this.reverseColor = reverseColor;
        this.start = start;
        this.end = end;
        this.reverseComplement = reverseComplement;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
    }

    public String getAnnotationId () {
        return this.annotationId;
    }

    public void setAnnotationId ( String annotationId ) {
        this.annotationId = annotationId;
    }

    public PersonTable getPersonTable () {
        return this.personTable;
    }

    public void setPersonTable ( PersonTable personTable ) {
        this.personTable = personTable;
    }

    public FeatureTable getFeatureTable () {
        return this.featureTable;
    }

    public void setFeatureTable ( FeatureTable featureTable ) {
        this.featureTable = featureTable;
    }

    public NucseqTable getNucseqTable () {
        return this.nucseqTable;
    }

    public void setNucseqTable ( NucseqTable nucseqTable ) {
        this.nucseqTable = nucseqTable;
    }

    @Override
    public String getName () {
        return this.name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public Integer getForwardColor () {
        return this.forwardColor;
    }

    public void setForwardColor ( Integer forwardColor ) {
        this.forwardColor = forwardColor;
    }

    public Integer getReverseColor () {
        return this.reverseColor;
    }

    public void setReverseColor ( Integer reverseColor ) {
        this.reverseColor = reverseColor;
    }

    public Integer getStart () {
        return this.start;
    }

    public void setStart ( Integer start ) {
        this.start = start;
    }

    public Integer getEnd () {
        return this.end;
    }

    public void setEnd ( Integer end ) {
        this.end = end;
    }

    public Boolean getReverseComplement () {
        return this.reverseComplement;
    }

    public void setReverseComplement ( Boolean reverseComplement ) {
        this.reverseComplement = reverseComplement;
    }

    public Date getDateCreated () {
        return this.dateCreated;
    }

    public void setDateCreated ( Date dateCreated ) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified () {
        return this.lastModified;
    }

    public void setLastModified ( Date lastModified ) {
        this.lastModified = lastModified;
    }
}

