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

package org.clothocad.tool.bglbrickdigester;

import java.awt.Window;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import org.clothocore.api.core.Collector;
import org.clothocore.api.data.Collection;
import org.clothocore.api.data.ObjBase;
import org.clothocore.api.data.ObjType;
import org.clothocore.api.plugin.ClothoTool;


/**
 *
 * @author J. Christopher Anderson
 */
public class connect implements ClothoTool {

    public connect() {
         _connect = this;
    }

    @Override
    public void launch() {
        Preferences _prefs = Preferences.userNodeForPackage(digestMultiple.class);
        String uuid = _prefs.get("DefaultCollection", "");
        Collection joshcoll = Collector.getCollection(uuid);
        if(joshcoll!=null) {
            digestMultiple v = new digestMultiple(joshcoll);
            pig.add(new WeakReference(v));
            return;
        }
        Collection chosen = new Collection();
        digestMultiple v = new digestMultiple(chosen);
        pig.add(new WeakReference(v));
    }

    @Override
    public void launch(ObjBase o) {
        if(o.getType().equals(ObjType.COLLECTION)) {
            Collection chosen = (Collection) o;
            digestMultiple v = new digestMultiple(chosen);
            pig.add(new WeakReference(v));
        }
    }

    @Override
    public void close() {
        for(WeakReference<Window> wf: pig) {
            Window gui = (Window) wf.get();
            if(gui!=null) {
                gui.dispose();
            }
        }
    }

    @Override
    public void init() {
    }

/*-----------------
     variables
 -----------------*/
private ArrayList<WeakReference<Window>> pig= new ArrayList<WeakReference<Window>>();
static connect _connect;
}
