package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import common.helpers.StepLogger;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends BasePage<DashboardPage> {
    private SelenideElement depositMoneyButton = $(Selectors.byText("💰 Deposit Money"));
    private SelenideElement makeATransferButton = $(Selectors.byText("🔄 Make a Transfer"));
    private SelenideElement profileHeaderButton = $(".profile-header");
    private SelenideElement userDashboard = $(Selectors.byText("User Dashboard"));

    @Override
    public String url() {
        return "/dashboard";
    }

    public DashboardPage pressDepositMoneyButton() {
        return StepLogger.uiStep("Press Deposit Money button", () -> {
            depositMoneyButton.click();
            return this;
        });
    }

    public DashboardPage pressMakeATransferButton() {
        return StepLogger.uiStep("Press Make a Transfer button", () -> {
            makeATransferButton.click();
            return this;
        });
    }

    public DashboardPage pressProfileHeader() {
        return StepLogger.uiStep("Press profile header", () -> {
            profileHeaderButton.click();
            return this;
        });
    }

    public DashboardPage checkUserDashboardTextIsVisible() {
        return StepLogger.uiStep("Check User Dashboard is visible", () -> {
            userDashboard.shouldBe(Condition.visible);
            return this;
        });
    }
}
