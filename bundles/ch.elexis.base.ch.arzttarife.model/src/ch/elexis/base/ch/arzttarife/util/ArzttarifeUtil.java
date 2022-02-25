package ch.elexis.base.ch.arzttarife.util;

import java.util.Collections;
import java.util.List;

import ch.elexis.base.ch.arzttarife.model.service.ArzttarifeModelServiceHolder;
import ch.elexis.base.ch.arzttarife.tarmed.ITarmedLeistung;
import ch.elexis.base.ch.arzttarife.tarmed.MandantType;
import ch.elexis.core.jpa.entities.Verrechnet;
import ch.elexis.core.model.IBillable;
import ch.elexis.core.model.IBilled;
import ch.elexis.core.model.IEncounter;
import ch.elexis.core.model.IMandator;
import ch.elexis.core.model.verrechnet.Constants;
import ch.rgw.tools.Money;

public class ArzttarifeUtil {
	
	private static String MANDANT_TYPE_EXTINFO_KEY = "ch.elexis.data.tarmed.mandant.type";
	
	/**
	 * Set the {@link MandantType} of the {@link IMandator}.
	 * 
	 * @param mandant
	 * @param type
	 */
	public static void setMandantType(IMandator mandator, MandantType type){
		mandator.setExtInfo(MANDANT_TYPE_EXTINFO_KEY, type.name());
	}
	
	/**
	 * Get the {@link MandantType} of the {@link IMandator}. If not found the default value is
	 * {@link MandantType#SPECIALIST}.
	 * 
	 * @param mandant
	 * @return
	 * @since 3.4
	 */
	public static MandantType getMandantType(IMandator mandator){
		Object typeObj = mandator.getExtInfo(MANDANT_TYPE_EXTINFO_KEY);
		if (typeObj instanceof String) {
			return MandantType.valueOf((String) typeObj);
		}
		return MandantType.SPECIALIST;
	}
	
	/**
	 * The the {@link Money} representation of the AL amount of the billed.
	 * 
	 * @param billed
	 * @return
	 */
	public static Money getALMoney(IBilled billed){
		// amount already includes secondary scale for non integer amounts
		double secondaryScale = 1.0;
		if (!billed.isNonIntegerAmount()) {
			secondaryScale = billed.getSecondaryScaleFactor();
		}
		return new Money((int) Math.round(getAL(billed) * billed.getFactor() * billed.getAmount()
			* billed.getPrimaryScaleFactor() * secondaryScale));
		
	}
	
	/**
	 * The the {@link Money} representation of the TL amount of the billed.
	 * 
	 * @param billed
	 * @return
	 */
	public static Money getTLMoney(IBilled billed){
		// amount already includes secondary scale for non integer amounts
		double secondaryScale = 1.0;
		if (!billed.isNonIntegerAmount()) {
			secondaryScale = billed.getSecondaryScaleFactor();
		}
		return new Money((int) Math.round(getTL(billed) * billed.getFactor() * billed.getAmount()
			* billed.getPrimaryScaleFactor() * secondaryScale));
	}
	
	/**
	 * Get the AL value of the {@link IBilled} from the ext info of it. If there is no such
	 * information present and the {@link IBillable} linked with the {@link IBilled} is a
	 * {@link ITarmedLeistung}, that AL value is returned as fallback.
	 * 
	 * @param billed
	 * @return
	 */
	public static double getAL(IBilled billed){
		// if price was changed, use TP as AL
		boolean changedPrice = billed.isChangedPrice();
		if (changedPrice) {
			return billed.getPoints();
		}
		String alString = (String) billed.getExtInfo(Verrechnet.EXT_VERRRECHNET_AL);
		if (alString != null) {
			try {
				return (int) Double.parseDouble(alString);
			} catch (NumberFormatException ne) {
				// ignore, try resolve from IVerrechenbar
			}
		}
		IBillable billable = billed.getBillable();
		if (billable instanceof ITarmedLeistung) {
			IEncounter encounter = billed.getEncounter();
			return encounter != null ? ((ITarmedLeistung) billable).getAL(encounter.getMandator())
					: ((ITarmedLeistung) billable).getAL();
		}
		return 0;
	}
	
	/**
	 * Get the TL value of the {@link IBilled} from the ext info of it. If there is no such
	 * information present and the {@link IBillable} linked with the {@link IBilled} is a
	 * {@link ITarmedLeistung}, that TL value is returned as fallback.
	 * 
	 * @param billed
	 * @return
	 */
	public static double getTL(IBilled billed){
		String tlString = (String) billed.getExtInfo(Verrechnet.EXT_VERRRECHNET_TL);
		if (tlString != null) {
			try {
				return (int) Double.parseDouble(tlString);
			} catch (NumberFormatException ne) {
				// ignore, try resolve from IVerrechenbar
			}
		}
		IBillable billable = billed.getBillable();
		if (billable instanceof ITarmedLeistung) {
			return ((ITarmedLeistung) billable).getTL();
		}
		return 0;
	}
	
	/**
	 * Get the tarmed side information of the {@link IBilled}. If there is no
	 * {@link ITarmedLeistung} referenced from the {@link IBilled} the returned value is always
	 * "none".
	 * 
	 * @param billed
	 * @return
	 */
	public static String getSide(IBilled billed){
		IBillable billable = billed.getBillable();
		if (billable instanceof ITarmedLeistung) {
			String side = (String) billed.getExtInfo(Constants.FLD_EXT_SIDE);
			if (Constants.SIDE_L.equalsIgnoreCase(side)) {
				return Constants.LEFT;
			} else if (Constants.SIDE_R.equalsIgnoreCase(side)) {
				return Constants.RIGHT;
			}
		}
		return "none";
	}
	
	/**
	 * Test if the billed is marked as an obligation. If there is no {@link ITarmedLeistung}
	 * referenced from the {@link IBilled} the returned value is always false.
	 * 
	 * @param billed
	 * @return
	 */
	public static boolean isObligation(IBilled billed){
		IBillable billable = billed.getBillable();
		if (billable instanceof ITarmedLeistung) {
			String obli = (String) billed.getExtInfo(Constants.FLD_EXT_PFLICHTLEISTUNG);
			if ((obli == null) || (Boolean.parseBoolean(obli))) {
				return true;
			}
		}
		return false;
	}
	
	private static List<String> availableLawsCache;
	
	public static List<String> getAvailableLaws(){
		return ArzttarifeModelServiceHolder.get()
			.getNamedQueryByName(String.class, ITarmedLeistung.class, "TarmedLeistungDistinctLaws")
			.executeWithParameters(Collections.emptyMap());
	}
	
	public static boolean isAvailableLaw(String law){
		if (availableLawsCache == null) {
			availableLawsCache = getAvailableLaws();
		}
		for (String available : availableLawsCache) {
			if (available.equalsIgnoreCase(law)) {
				return true;
			}
		}
		return false;
	}
}
