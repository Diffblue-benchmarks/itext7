package com.itextpdf.kernel.pdf.tagging;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;

public class PdfObjRef extends PdfMcr<PdfDictionary> {

    private static final long serialVersionUID = 344098256404114906L;

	public PdfObjRef(PdfDictionary pdfObject, PdfStructElem parent) {
        super(pdfObject, parent);
    }

    public PdfObjRef(PdfAnnotation annot, PdfStructElem parent) {
        super(new PdfDictionary(), parent);
        PdfDictionary parentObject = parent.getPdfObject();
        ensureObjectIsAddedToDocument(parentObject);

        PdfDictionary dict = (PdfDictionary) getPdfObject();
        dict.put(PdfName.Type, PdfName.OBJR);
        dict.put(PdfName.Obj, annot.tag(parentObject.getIndirectReference().getDocument()).getPdfObject());
    }

    @Override
    public Integer getMcid() {
        return null;
    }

    @Override
    public PdfDictionary getPageObject() {
        PdfDictionary page = ((PdfDictionary)getPdfObject()).getAsDictionary(PdfName.Pg);
        if (page == null)
            page = parent.getPdfObject().getAsDictionary(PdfName.Pg);
        return page;
    }

    public PdfObject getReferencedObject() {
        return ((PdfDictionary) getPdfObject()).get(PdfName.Obj);
    }

}
