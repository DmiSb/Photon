package ru.dmisb.photon.screens.selector.filter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ru.dmisb.photon.data.dto.FilterDto;

@SuppressWarnings("unused")
public class FilterViewModel extends BaseObservable {
    private boolean meat;
    private boolean fish;
    private boolean vegetables;
    private boolean fruit;
    private boolean cheese;
    private boolean desert;
    private boolean drinks;

    private boolean nuancesRed;
    private boolean nuancesOrange;
    private boolean nuancesYellow;
    private boolean nuancesGreen;
    private boolean nuancesLightBlue;
    private boolean nuancesBlue;
    private boolean nuancesViolet;
    private boolean nuancesBrown;
    private boolean nuancesBlack;
    private boolean nuancesWhite;

    private boolean decorSimple;
    private boolean decorHoliday;

    private boolean temperatureHot;
    private boolean temperatureMiddle;
    private boolean temperatureCold;

    private boolean lightNatural;
    private boolean lightSynthetic;
    private boolean lightMixed;

    private boolean lightDirDirect;
    private boolean lightDirBack;
    private boolean lightDirSide;
    private boolean lightDirMixed;

    private boolean lightCount1;
    private boolean lightCount2;
    private boolean lightCount3;
    
    private FilterDto oldFilter;

    @Bindable
    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
        notifyChange();
    }

    @Bindable
    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
        notifyChange();
    }

    @Bindable
    public boolean isVegetables() {
        return vegetables;
    }

    public void setVegetables(boolean vegetables) {
        this.vegetables = vegetables;
        notifyChange();
    }

    @Bindable
    public boolean isFruit() {
        return fruit;
    }

    public void setFruit(boolean fruit) {
        this.fruit = fruit;
        notifyChange();
    }

    @Bindable
    public boolean isCheese() {
        return cheese;
    }

    public void setCheese(boolean cheese) {
        this.cheese = cheese;
        notifyChange();
    }

    @Bindable
    public boolean isDesert() {
        return desert;
    }

    public void setDesert(boolean desert) {
        this.desert = desert;
        notifyChange();
    }

    @Bindable
    public boolean isDrinks() {
        return drinks;
    }

    public void setDrinks(boolean drinks) {
        this.drinks = drinks;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesRed() {
        return nuancesRed;
    }

    public void setNuancesRed(boolean nuancesRed) {
        this.nuancesRed = nuancesRed;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesOrange() {
        return nuancesOrange;
    }

    public void setNuancesOrange(boolean nuancesOrange) {
        this.nuancesOrange = nuancesOrange;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesYellow() {
        return nuancesYellow;
    }

    public void setNuancesYellow(boolean nuancesYellow) {
        this.nuancesYellow = nuancesYellow;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesGreen() {
        return nuancesGreen;
    }

    public void setNuancesGreen(boolean nuancesGreen) {
        this.nuancesGreen = nuancesGreen;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesLightBlue() {
        return nuancesLightBlue;
    }

    public void setNuancesLightBlue(boolean nuancesLightBlue) {
        this.nuancesLightBlue = nuancesLightBlue;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesBlue() {
        return nuancesBlue;
    }

    public void setNuancesBlue(boolean nuancesBlue) {
        this.nuancesBlue = nuancesBlue;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesViolet() {
        return nuancesViolet;
    }

    public void setNuancesViolet(boolean nuancesViolet) {
        this.nuancesViolet = nuancesViolet;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesBrown() {
        return nuancesBrown;
    }

    public void setNuancesBrown(boolean nuancesBrown) {
        this.nuancesBrown = nuancesBrown;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesBlack() {
        return nuancesBlack;
    }

    public void setNuancesBlack(boolean nuancesBlack) {
        this.nuancesBlack = nuancesBlack;
        notifyChange();
    }

    @Bindable
    public boolean isNuancesWhite() {
        return nuancesWhite;
    }

    public void setNuancesWhite(boolean nuancesWhite) {
        this.nuancesWhite = nuancesWhite;
        notifyChange();
    }

    @Bindable
    public boolean isDecorSimple() {
        return decorSimple;
    }

    public void setDecorSimple(boolean decorSimple) {
        this.decorSimple = decorSimple;
        notifyChange();
    }

    @Bindable
    public boolean isDecorHoliday() {
        return decorHoliday;
    }

    public void setDecorHoliday(boolean decorHoliday) {
        this.decorHoliday = decorHoliday;
        notifyChange();
    }

    @Bindable
    public boolean isTemperatureHot() {
        return temperatureHot;
    }

    public void setTemperatureHot(boolean temperatureHot) {
        this.temperatureHot = temperatureHot;
        notifyChange();
    }

    @Bindable
    public boolean isTemperatureMiddle() {
        return temperatureMiddle;
    }

    public void setTemperatureMiddle(boolean temperatureMiddle) {
        this.temperatureMiddle = temperatureMiddle;
        notifyChange();
    }

    @Bindable
    public boolean isTemperatureCold() {
        return temperatureCold;
    }

    public void setTemperatureCold(boolean temperatureCold) {
        this.temperatureCold = temperatureCold;
        notifyChange();
    }

    @Bindable
    public boolean isLightNatural() {
        return lightNatural;
    }

    public void setLightNatural(boolean lightNatural) {
        this.lightNatural = lightNatural;
        notifyChange();
    }

    @Bindable
    public boolean isLightSynthetic() {
        return lightSynthetic;
    }

    public void setLightSynthetic(boolean lightSynthetic) {
        this.lightSynthetic = lightSynthetic;
        notifyChange();
    }

    @Bindable
    public boolean isLightMixed() {
        return lightMixed;
    }

    public void setLightMixed(boolean lightMixed) {
        this.lightMixed = lightMixed;
        notifyChange();
    }

    @Bindable
    public boolean isLightDirDirect() {
        return lightDirDirect;
    }

    public void setLightDirDirect(boolean lightDirDirect) {
        this.lightDirDirect = lightDirDirect;
        notifyChange();
    }

    @Bindable
    public boolean isLightDirBack() {
        return lightDirBack;
    }

    public void setLightDirBack(boolean lightDirBack) {
        this.lightDirBack = lightDirBack;
        notifyChange();
    }

    @Bindable
    public boolean isLightDirSide() {
        return lightDirSide;
    }

    public void setLightDirSide(boolean lightDirSide) {
        this.lightDirSide = lightDirSide;
        notifyChange();
    }

    @Bindable
    public boolean isLightDirMixed() {
        return lightDirMixed;
    }

    public void setLightDirMixed(boolean lightDirMixed) {
        this.lightDirMixed = lightDirMixed;
        notifyChange();
    }

    @Bindable
    public boolean isLightCount1() {
        return lightCount1;
    }

    public void setLightCount1(boolean lightCount1) {
        this.lightCount1 = lightCount1;
        notifyChange();
    }

    @Bindable
    public boolean isLightCount2() {
        return lightCount2;
    }

    public void setLightCount2(boolean lightCount2) {
        this.lightCount2 = lightCount2;
        notifyChange();
    }

    @Bindable
    public boolean isLightCount3() {
        return lightCount3;
    }

    public void setLightCount3(boolean lightCount3) {
        this.lightCount3 = lightCount3;
        notifyChange();
    }

    public void setFilter(FilterDto filter) {
        this.oldFilter = filter;
        setMeat(filter.isMeat());
        setFish(filter.isFish());
        setVegetables(filter.isVegetables());
        setFruit(filter.isFruit());
        setCheese(filter.isCheese());
        setDesert(filter.isDesert());
        setDrinks(filter.isDrinks());

        setNuancesRed(filter.isNuancesRed());
        setNuancesOrange(filter.isNuancesOrange());
        setNuancesYellow(filter.isNuancesYellow());
        setNuancesGreen(filter.isNuancesGreen());
        setNuancesLightBlue(filter.isNuancesLightBlue());
        setNuancesBlue(filter.isNuancesBlue());
        setNuancesViolet(filter.isNuancesViolet());
        setNuancesBrown(filter.isNuancesBrown());
        setNuancesBlack(filter.isNuancesBlack());
        setNuancesWhite(filter.isNuancesWhite());

        setDecorSimple(filter.isDecorSimple());
        setDecorHoliday(filter.isDecorHoliday());

        setTemperatureHot(filter.isTemperatureHot());
        setTemperatureMiddle(filter.isTemperatureMiddle());
        setTemperatureCold(filter.isTemperatureCold());

        setLightNatural(filter.isLightNatural());
        setLightSynthetic(filter.isLightSynthetic());
        setLightMixed(filter.isLightMixed());

        setLightDirDirect(filter.isLightDirDirect());
        setLightDirBack(filter.isLightDirBack());
        setLightDirSide(filter.isLightDirSide());
        setLightMixed(filter.isLightMixed());

        setLightCount1(filter.isLightCount1());
        setLightCount2(filter.isLightCount2());
        setLightCount3(filter.isLightCount3());
        notifyChange();
    }

    public boolean filterChanged() {
        if (oldFilter != null)
            return meat != oldFilter.isMeat() ||
                    fish != oldFilter.isFish() ||
                    vegetables != oldFilter.isVegetables() ||
                    fruit != oldFilter.isFruit() ||
                    cheese != oldFilter.isCheese() ||
                    desert != oldFilter.isDesert() ||
                    drinks != oldFilter.isDrinks() ||

                    nuancesRed != oldFilter.isNuancesRed() ||
                    nuancesOrange != oldFilter.isNuancesOrange() ||
                    nuancesYellow != oldFilter.isNuancesYellow() ||
                    nuancesGreen != oldFilter.isNuancesGreen() ||
                    nuancesLightBlue != oldFilter.isNuancesLightBlue() ||
                    nuancesBlue != oldFilter.isNuancesBlue() ||
                    nuancesViolet != oldFilter.isNuancesViolet() ||
                    nuancesBrown != oldFilter.isNuancesBrown() ||
                    nuancesBlack != oldFilter.isNuancesBlack() ||
                    nuancesWhite != oldFilter.isNuancesWhite() ||

                    decorSimple != oldFilter.isDecorSimple() ||
                    decorHoliday != oldFilter.isDecorHoliday() ||

                    temperatureHot != oldFilter.isTemperatureHot() ||
                    temperatureMiddle != oldFilter.isTemperatureMiddle() ||
                    temperatureCold != oldFilter.isTemperatureCold() ||

                    lightNatural != oldFilter.isLightNatural() ||
                    lightSynthetic != oldFilter.isLightSynthetic() ||
                    lightMixed != oldFilter.isLightMixed() ||

                    lightDirDirect != oldFilter.isLightDirDirect() ||
                    lightDirBack != oldFilter.isLightDirBack() ||
                    lightDirSide != oldFilter.isLightDirSide() ||
                    lightMixed != oldFilter.isLightMixed() ||

                    lightCount1 != oldFilter.isLightCount1() ||
                    lightCount2 != oldFilter.isLightCount2() ||
                    lightCount3 != oldFilter.isLightCount3();
        else
            return isActive();
    }

    @Bindable
    public boolean isActive() {
        return meat || fish || vegetables || fruit || cheese || desert || drinks ||
                nuancesRed || nuancesOrange || nuancesYellow || nuancesGreen || nuancesLightBlue ||
                nuancesBlue || nuancesViolet || nuancesBrown || nuancesBlack || nuancesWhite ||
                decorSimple || decorHoliday ||
                temperatureHot || temperatureMiddle || temperatureCold ||
                lightNatural || lightSynthetic || lightMixed ||
                lightDirDirect || lightDirBack || lightDirSide || lightDirMixed ||
                lightCount1 || lightCount2 || lightCount3;
    }
}
