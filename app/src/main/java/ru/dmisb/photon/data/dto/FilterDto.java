package ru.dmisb.photon.data.dto;

import ru.dmisb.photon.screens.selector.filter.FilterViewModel;

@SuppressWarnings("unused")
public class FilterDto {
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

    public FilterDto() {
    }

    public FilterDto(FilterViewModel viewModel) {
        this.meat = viewModel.isMeat();
        this.fish = viewModel.isFish();
        this.vegetables = viewModel.isVegetables();
        this.fruit = viewModel.isFruit();
        this.cheese = viewModel.isCheese();
        this.desert = viewModel.isDesert();
        this.drinks = viewModel.isDrinks();
        this.nuancesRed = viewModel.isNuancesRed();
        this.nuancesOrange = viewModel.isNuancesOrange();
        this.nuancesYellow = viewModel.isNuancesYellow();
        this.nuancesGreen = viewModel.isNuancesGreen();
        this.nuancesLightBlue = viewModel.isNuancesLightBlue();
        this.nuancesBlue = viewModel.isNuancesBlue();
        this.nuancesViolet = viewModel.isNuancesViolet();
        this.nuancesBrown = viewModel.isNuancesBrown();
        this.nuancesBlack = viewModel.isNuancesBlack();
        this.nuancesWhite = viewModel.isNuancesWhite();
        this.decorSimple = viewModel.isDecorSimple();
        this.decorHoliday = viewModel.isDecorHoliday();
        this.temperatureHot = viewModel.isTemperatureHot();
        this.temperatureMiddle = viewModel.isTemperatureMiddle();
        this.temperatureCold = viewModel.isTemperatureCold();
        this.lightNatural = viewModel.isLightNatural();
        this.lightSynthetic = viewModel.isLightSynthetic();
        this.lightMixed = viewModel.isLightMixed();
        this.lightDirDirect = viewModel.isLightDirDirect();
        this.lightDirBack = viewModel.isLightDirBack();
        this.lightDirSide = viewModel.isLightDirSide();
        this.lightDirMixed = viewModel.isLightDirMixed();
        this.lightCount1 = viewModel.isLightCount1();
        this.lightCount2 = viewModel.isLightCount2();
        this.lightCount3 = viewModel.isLightCount3();
    }

    public boolean isMeat() {
        return meat;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isVegetables() {
        return vegetables;
    }

    public boolean isFruit() {
        return fruit;
    }

    public boolean isCheese() {
        return cheese;
    }

    public boolean isDesert() {
        return desert;
    }

    public boolean isDrinks() {
        return drinks;
    }

    public boolean isNuancesRed() {
        return nuancesRed;
    }

    public boolean isNuancesOrange() {
        return nuancesOrange;
    }

    public boolean isNuancesYellow() {
        return nuancesYellow;
    }

    public boolean isNuancesGreen() {
        return nuancesGreen;
    }

    public boolean isNuancesLightBlue() {
        return nuancesLightBlue;
    }

    public boolean isNuancesBlue() {
        return nuancesBlue;
    }

    public boolean isNuancesViolet() {
        return nuancesViolet;
    }

    public boolean isNuancesBrown() {
        return nuancesBrown;
    }

    public boolean isNuancesBlack() {
        return nuancesBlack;
    }

    public boolean isNuancesWhite() {
        return nuancesWhite;
    }

    public boolean isDecorSimple() {
        return decorSimple;
    }

    public boolean isDecorHoliday() {
        return decorHoliday;
    }

    public boolean isTemperatureHot() {
        return temperatureHot;
    }

    public boolean isTemperatureMiddle() {
        return temperatureMiddle;
    }

    public boolean isTemperatureCold() {
        return temperatureCold;
    }

    public boolean isLightNatural() {
        return lightNatural;
    }

    public boolean isLightSynthetic() {
        return lightSynthetic;
    }

    public boolean isLightMixed() {
        return lightMixed;
    }

    public boolean isLightDirDirect() {
        return lightDirDirect;
    }

    public boolean isLightDirBack() {
        return lightDirBack;
    }

    public boolean isLightDirSide() {
        return lightDirSide;
    }

    public boolean isLightDirMixed() {
        return lightDirMixed;
    }

    public boolean isLightCount1() {
        return lightCount1;
    }

    public boolean isLightCount2() {
        return lightCount2;
    }

    public boolean isLightCount3() {
        return lightCount3;
    }

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
