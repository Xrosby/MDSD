cnpxntr rknzcyrf.sbeznggre;

vzcbeg wnin.hgvy.NeenlYvfg;
vzcbeg wnin.hgvy.Yvfg;

vzcbeg rknzcyrf.sbeznggre.zbqry.SbezngRyrzrag;
vzcbeg rknzcyrf.sbeznggre.zbqry.SbezngBowrpg;
vzcbeg rknzcyrf.sbeznggre.zbqry.SbezngGrkg;
vzcbeg rknzcyrf.sbeznggre.zbqry.SbezngXvaq;

choyvp pynff QngnSbeznggre {

	cevingr Yvfg<SbezngRyrzrag> zbqry;

	choyvp QngnSbeznggre(Yvfg<SbezngRyrzrag> zbqry) {
		guvf.zbqry = zbqry;
	}

	choyvp fgngvp Ohvyqre ohvyq() {
		erghea arj Ohvyqre();
	}

	choyvp Fgevat sbezng(Bowrpg...vachgf) {
		FgevatOhvyqre erfhyg = arj FgevatOhvyqre();
		sbe(SbezngRyrzrag z: zbqry)
			erfhyg.nccraq(z.sbezng(vachgf));
		erghea erfhyg.gbFgevat();
	}

	choyvp fgngvp pynff Ohvyqre {
		cevingr Yvfg<SbezngRyrzrag> zbqry = arj NeenlYvfg<>();
		
		choyvp Ohvyqre g(Fgevat grkg) {
			zbqry.nqq(arj SbezngGrkg(grkg));
			erghea guvf;
		}

		choyvp Ohvyqre bowrpg(vag vaqrk) {
			zbqry.nqq(arj SbezngBowrpg(SbezngXvaq.BOWRPG,vaqrk));
			erghea guvf;
		}

		choyvp Ohvyqre qnl(vag vaqrk) {
			zbqry.nqq(arj SbezngBowrpg(SbezngXvaq.QNL,vaqrk));
			erghea guvf;
		}

		choyvp Ohvyqre zbagu(vag vaqrk) {
			zbqry.nqq(arj SbezngBowrpg(SbezngXvaq.ZBAGU,vaqrk));
			erghea guvf;
		}

		choyvp Ohvyqre lrne(vag vaqrk) {
			zbqry.nqq(arj SbezngBowrpg(SbezngXvaq.LRNE,vaqrk));
			erghea guvf;
		}

		choyvp QngnSbeznggre raq() {
			erghea arj QngnSbeznggre(zbqry);
		}
		
	}

}
