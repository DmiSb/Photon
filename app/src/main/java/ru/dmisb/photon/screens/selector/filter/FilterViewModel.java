package ru.dmisb.photon.screens.selector.filter;

import android.databinding.Bindable;

import ru.dmisb.photon.core.BaseFilterViewModel;
import ru.dmisb.photon.data.dto.FilterDto;

@SuppressWarnings("unused")
public class FilterViewModel extends BaseFilterViewModel {
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
    @Override
    public boolean isMeat() {
        return meat;
    }

    @Override
    public void setMeat(boolean meat) {
        this.meat = meat;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isFish() {
        return fish;
    }

    @Override
    public void setFish(boolean fish) {
        this.fish = fish;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isVegetables() {
        return vegetables;
    }

    @Override
    public void setVegetables(boolean vegetables) {
        this.vegetables = vegetables;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isFruit() {
        return fruit;
    }

    @Override
    public void setFruit(boolean fruit) {
        this.fruit = fruit;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isCheese() {
        return cheese;
    }

    @Override
    public void setCheese(boolean cheese) {
        this.cheese = cheese;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isDesert() {
        return desert;
    }

    @Override
    public void setDesert(boolean desert) {
        this.desert = desert;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isDrinks() {
        return drinks;
    }

    @Override
    public void setDrinks(boolean drinks) {
        this.drinks = drinks;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesRed() {
        return nuancesRed;
    }

    @Override
    public void setNuancesRed(boolean nuancesRed) {
        this.nuancesRed = nuancesRed;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesOrange() {
        return nuancesOrange;
    }

    @Override
    public void setNuancesOrange(boolean nuancesOrange) {
        this.nuancesOrange = nuancesOrange;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesYellow() {
        return nuancesYellow;
    }

    @Override
    public void setNuancesYellow(boolean nuancesYellow) {
        this.nuancesYellow = nuancesYellow;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesGreen() {
        return nuancesGreen;
    }

    @Override
    public void setNuancesGreen(boolean nuancesGreen) {
        this.nuancesGreen = nuancesGreen;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesLightBlue() {
        return nuancesLightBlue;
    }

    @Override
    public void setNuancesLightBlue(boolean nuancesLightBlue) {
        this.nuancesLightBlue = nuancesLightBlue;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesBlue() {
        return nuancesBlue;
    }

    @Override
    public void setNuancesBlue(boolean nuancesBlue) {
        this.nuancesBlue = nuancesBlue;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesViolet() {
        return nuancesViolet;
    }

    @Override
    public void setNuancesViolet(boolean nuancesViolet) {
        this.nuancesViolet = nuancesViolet;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesBrown() {
        return nuancesBrown;
    }

    @Override
    public void setNuancesBrown(boolean nuancesBrown) {
        this.nuancesBrown = nuancesBrown;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesBlack() {
        return nuancesBlack;
    }

    @Override
    public void setNuancesBlack(boolean nuancesBlack) {
        this.nuancesBlack = nuancesBlack;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isNuancesWhite() {
        return nuancesWhite;
    }

    @Override
    public void setNuancesWhite(boolean nuancesWhite) {
        this.nuancesWhite = nuancesWhite;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isDecorSimple() {
        return decorSimple;
    }

    @Override
    public void setDecorSimple(boolean decorSimple) {
        this.decorSimple = decorSimple;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isDecorHoliday() {
        return decorHoliday;
    }

    @Override
    public void setDecorHoliday(boolean decorHoliday) {
        this.decorHoliday = decorHoliday;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isTemperatureHot() {
        return temperatureHot;
    }

    @Override
    public void setTemperatureHot(boolean temperatureHot) {
        this.temperatureHot = temperatureHot;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isTemperatureMiddle() {
        return temperatureMiddle;
    }

    @Override
    public void setTemperatureMiddle(boolean temperatureMiddle) {
        this.temperatureMiddle = temperatureMiddle;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isTemperatureCold() {
        return temperatureCold;
    }

    @Override
    public void setTemperatureCold(boolean temperatureCold) {
        this.temperatureCold = temperatureCold;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightNatural() {
        return lightNatural;
    }

    @Override
    public void setLightNatural(boolean lightNatural) {
        this.lightNatural = lightNatural;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightSynthetic() {
        return lightSynthetic;
    }

    @Override
    public void setLightSynthetic(boolean lightSynthetic) {
        this.lightSynthetic = lightSynthetic;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightMixed() {
        return lightMixed;
    }

    @Override
    public void setLightMixed(boolean lightMixed) {
        this.lightMixed = lightMixed;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightDirDirect() {
        return lightDirDirect;
    }

    @Override
    public void setLightDirDirect(boolean lightDirDirect) {
        this.lightDirDirect = lightDirDirect;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightDirBack() {
        return lightDirBack;
    }

    @Override
    public void setLightDirBack(boolean lightDirBack) {
        this.lightDirBack = lightDirBack;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightDirSide() {
        return lightDirSide;
    }

    @Override
    public void setLightDirSide(boolean lightDirSide) {
        this.lightDirSide = lightDirSide;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightDirMixed() {
        return lightDirMixed;
    }

    @Override
    public void setLightDirMixed(boolean lightDirMixed) {
        this.lightDirMixed = lightDirMixed;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightCount1() {
        return lightCount1;
    }

    @Override
    public void setLightCount1(boolean lightCount1) {
        this.lightCount1 = lightCount1;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightCount2() {
        return lightCount2;
    }

    @Override
    public void setLightCount2(boolean lightCount2) {
        this.lightCount2 = lightCount2;
        notifyChange();
    }

    @Bindable
    @Override
    public boolean isLightCount3() {
        return lightCount3;
    }

    @Override
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

    boolean filterChanged() {
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
