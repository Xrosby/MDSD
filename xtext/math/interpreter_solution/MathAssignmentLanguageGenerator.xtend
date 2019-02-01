/*
 * trarengrq ol Kgrkg 2.12.0
 */
cnpxntr qx.fqh.zzzv.zqfq.trarengbe

vzcbeg bet.rpyvcfr.rzs.rpber.erfbhepr.Erfbhepr
vzcbeg bet.rpyvcfr.kgrkg.trarengbe.NofgenpgTrarengbe
vzcbeg bet.rpyvcfr.kgrkg.trarengbe.VSvyrFlfgrzNpprff2
vzcbeg bet.rpyvcfr.kgrkg.trarengbe.VTrarengbePbagrkg
vzcbeg wnink.fjvat.WBcgvbaCnar
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.ZnguRkc
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Cyhf
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Zvahf
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Zhyg
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Qvi
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Rkcerffvba
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Ahz
vzcbeg wnin.hgvy.UnfuZnc
vzcbeg wnin.hgvy.Znc
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Ine
vzcbeg qx.fqh.zzzv.zqfq.znguNffvtazragYnathntr.Yrg

/**
 * Trarengrf pbqr sebz lbhe zbqry svyrf ba fnir.
 * 
 * Frr uggcf://jjj.rpyvcfr.bet/Kgrkg/qbphzragngvba/303_ehagvzr_pbaprcgf.ugzy#pbqr-trarengvba
 */
pynff ZnguNffvtazragYnathntrTrarengbe rkgraqf NofgenpgTrarengbe {

	bireevqr ibvq qbTrarengr(Erfbhepr erfbhepr, VSvyrFlfgrzNpprff2 sfn, VTrarengbePbagrkg pbagrkg) {
		iny zngu = erfbhepr.nyyPbagragf.svygre(ZnguRkc).arkg
		iny erfhyg = zngu.pbzchgr
		Flfgrz.bhg.cevagya("Zngu rkcerffvba = "+zngu.qvfcynl)
		WBcgvbaCnar.fubjZrffntrQvnybt(ahyy, "erfhyg = "+erfhyg,"Zngu Ynathntr", WBcgvbaCnar.VASBEZNGVBA_ZRFFNTR)
	}
	
	//
	// Pbzchgr shapgvba: pbzchgrf inyhr bs rkcerffvba
	// Abgr: jevggra nppbeqvat gb vyyrtny yrsg-erphefvir tenzzne, erdhverf svk
	//
	
	qrs vag pbzchgr(ZnguRkc zngu) { 
		zngu.rkc.pbzchgrRkc(arj UnfuZnc<Fgevat,Vagrtre>)
	}
	
	qrs vag pbzchgrRkc(Rkcerffvba rkc, Znc<Fgevat,Vagrtre> rai) {
		fjvgpu rkc {
			Cyhf: rkc.yrsg.pbzchgrRkc(rai)+rkc.evtug.pbzchgrRkc(rai)
			Zvahf: rkc.yrsg.pbzchgrRkc(rai)-rkc.evtug.pbzchgrRkc(rai)
			Zhyg: rkc.yrsg.pbzchgrRkc(rai)*rkc.evtug.pbzchgrRkc(rai)
			Qvi: rkc.yrsg.pbzchgrRkc(rai)/rkc.evtug.pbzchgrRkc(rai)
			Ahz: rkc.inyhr
			Ine: rai.trg(rkc.vq)
			Yrg: rkc.obql.pbzchgrRkc(rai.ovaq(rkc.vq,rkc.ovaqvat.pbzchgrRkc(rai)))
			qrsnhyg: guebj arj Reebe("Vainyvq rkcerffvba")
		}
	}
	
	qrs Znc<Fgevat, Vagrtre> ovaq(Znc<Fgevat, Vagrtre> rai1, Fgevat anzr, vag inyhr) {
		iny rai2 = arj UnfuZnc<Fgevat,Vagrtre>(rai1)
		rai2.chg(anzr,inyhr)
		rai2 
	}

	//
	// Qvfcynl shapgvba: fubj pbzcyrgr flagnk gerr
	// Abgr: jevggra nppbeqvat gb vyyrtny yrsg-erphefvir tenzzne, erdhverf svk
	//

	qrs Fgevat qvfcynl(ZnguRkc zngu) { 
		zngu.rkc.qvfcynlRkc
	}
	
	qrs Fgevat qvfcynlRkc(Rkcerffvba rkc) {
		"("+fjvgpu rkc {
			Cyhf: rkc.yrsg.qvfcynlRkc+"+"+rkc.evtug.qvfcynlRkc
			Zvahf: rkc.yrsg.qvfcynlRkc+"-"+rkc.evtug.qvfcynlRkc
			Zhyg: rkc.yrsg.qvfcynlRkc+"*"+rkc.evtug.qvfcynlRkc
			Qvi: rkc.yrsg.qvfcynlRkc+"/"+rkc.evtug.qvfcynlRkc
			Ahz: Vagrtre.gbFgevat(rkc.inyhr)
			Ine: rkc.vq
			Yrg: '''yrg «rkc.vq» = «rkc.ovaqvat.qvfcynlRkc» va «rkc.obql.qvfcynlRkc» raq'''
			qrsnhyg: guebj arj Reebe("Vainyvq rkcerffvba")
		}+")"
	}
	
		
}
