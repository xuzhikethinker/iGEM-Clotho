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



/**
 * SampleDataXrefId generated by hbm2java
 */
public class SampleDataXrefId  implements java.io.Serializable {


     private String sampleId;
     private String sampleDataId;

    public SampleDataXrefId() {
    }

    public SampleDataXrefId(String sampleId, String sampleDataId) {
       this.sampleId = sampleId;
       this.sampleDataId = sampleDataId;
    }
   
    public String getSampleId() {
        return this.sampleId;
    }
    
    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }
    public String getSampleDataId() {
        return this.sampleDataId;
    }
    
    public void setSampleDataId(String sampleDataId) {
        this.sampleDataId = sampleDataId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SampleDataXrefId) ) return false;
		 SampleDataXrefId castOther = ( SampleDataXrefId ) other; 
         
		 return ( (this.getSampleId()==castOther.getSampleId()) || ( this.getSampleId()!=null && castOther.getSampleId()!=null && this.getSampleId().equals(castOther.getSampleId()) ) )
 && ( (this.getSampleDataId()==castOther.getSampleDataId()) || ( this.getSampleDataId()!=null && castOther.getSampleDataId()!=null && this.getSampleDataId().equals(castOther.getSampleDataId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSampleId() == null ? 0 : this.getSampleId().hashCode() );
         result = 37 * result + ( getSampleDataId() == null ? 0 : this.getSampleDataId().hashCode() );
         return result;
   }   


}


