package org.clothocad.tool.primerdesigner;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import org.openide.util.Exceptions;

/**
 *
 * @author Jenhan Tao
 */
public class PrimerDesignerListener implements DocumentListener {

    PrimerDesignerListener(DesignFrame df) {
        _view = df;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        Highlighter hl = _view.getSequenceTextField().getHighlighter();
        Highlight[] highlights = hl.getHighlights();
        for (int i = 0; i < highlights.length; i++) {
            javax.swing.text.Highlighter.Highlight u = highlights[i];
            //modified only when the evt is add at the end of the old ORF hightlight, thus end of evt is end of u
            if ((u.getStartOffset() < e.getOffset())
                    && (u.getEndOffset() == e.getOffset() + e.getLength()) //&& (u.getPainter() instanceof org.clothocad.util.highlight.ClothoHighlightPainter_Underline) //commented
                    ) {
                try {
                    hl.changeHighlight(u, u.getStartOffset(), u.getEndOffset() - e.getLength());
                } catch (javax.swing.text.BadLocationException ble) {
                    ble.printStackTrace();
                }
            } //if add in the middle of the ORF hightlight, delete the hightlight
            else if ((u.getStartOffset() < e.getOffset())
                    && (u.getEndOffset() > e.getOffset() + e.getLength()) //commented
                    //&& (u.getPainter() instanceof org.clothocad.util.highlight.ClothoHighlightPainter_Underline)
                    ) {
                hl.removeHighlight(u);
            }
        }
        _view.updateLabels();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Highlighter hl = _view.getSequenceTextField().getHighlighter();
        Highlight[] highlights = hl.getHighlights();
        for (int i = 0; i < highlights.length; i++) {
            javax.swing.text.Highlighter.Highlight u = highlights[i];
            //deleting before a higlight
            if (e.getOffset() < u.getStartOffset()) {
                try {
                    hl.changeHighlight(u, u.getStartOffset() - e.getLength() + 1, u.getEndOffset() - e.getLength() + 1);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
                //deleting from the middle
            } else if ((u.getStartOffset() <= e.getOffset()) && (u.getEndOffset() >= e.getOffset() + e.getLength())) {
                hl.removeHighlight(u);
                //deleting at the end of a highlight
            } else if ((u.getEndOffset() == e.getOffset())) {
                hl.removeHighlight(u);
            }

        }
        _view.updateLabels();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        _view.updateLabels();
    }
    private DesignFrame _view;
}
