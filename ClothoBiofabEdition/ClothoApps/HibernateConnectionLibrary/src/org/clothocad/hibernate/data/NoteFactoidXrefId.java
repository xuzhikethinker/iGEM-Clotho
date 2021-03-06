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
 * NoteFactoidXrefId generated by hbm2java
 */
public class NoteFactoidXrefId  implements java.io.Serializable {


     private String noteId;
     private String factoidId;

    public NoteFactoidXrefId() {
    }

    public NoteFactoidXrefId(String noteId, String factoidId) {
       this.noteId = noteId;
       this.factoidId = factoidId;
    }
   
    public String getNoteId() {
        return this.noteId;
    }
    
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
    public String getFactoidId() {
        return this.factoidId;
    }
    
    public void setFactoidId(String factoidId) {
        this.factoidId = factoidId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NoteFactoidXrefId) ) return false;
		 NoteFactoidXrefId castOther = ( NoteFactoidXrefId ) other; 
         
		 return ( (this.getNoteId()==castOther.getNoteId()) || ( this.getNoteId()!=null && castOther.getNoteId()!=null && this.getNoteId().equals(castOther.getNoteId()) ) )
 && ( (this.getFactoidId()==castOther.getFactoidId()) || ( this.getFactoidId()!=null && castOther.getFactoidId()!=null && this.getFactoidId().equals(castOther.getFactoidId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getNoteId() == null ? 0 : this.getNoteId().hashCode() );
         result = 37 * result + ( getFactoidId() == null ? 0 : this.getFactoidId().hashCode() );
         return result;
   }   


}


