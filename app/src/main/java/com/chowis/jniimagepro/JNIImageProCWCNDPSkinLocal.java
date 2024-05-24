package com.chowis.jniimagepro;

public class JNIImageProCWCNDPSkinLocal
{
	static 
	{
		System.loadLibrary("JNICNDPSkinLocal");
	}
	
	public native String getVersionJni();
	public native String getMakeDateJni();
	public native double reSizingJni(String sInputPath, String sOutputPath, int xSize, int ySize);
	public native double blurryJni(String sInputPath, String sOutputPath);
	public native double colordJni(String sInputPath, String sOutputPath);
	public native String featureExtractionJni(String sInputPath);
	
	public native String skinToneJni(String sInputPath, int nType); 
	public native String skinTone2(String sInputPath, String sOutputPath, String sChart, int colorPoint, int nType);
	public native double skinClarityJni(String sInputPath, boolean bClarityIn);
	public native int[] getMedianJni(String sInputPath);
	public native int getSkinAgeJni(int nAge, int nPore, int nSpot, int nWrinkle, int nAcne, int nHydration);
	public native double skinScalingJni(String sInputPath, String sOutputPath);
	public native double skinScabsJni(String sInputPath, String sOutputPath);
	public native int skinBlackheadJni(String sInputPathUV, String sInputPathPore, String sOutputPath);
	public native double fitzpatrickDetectionJni(String sInputPath, int nType);
	public native double skinTextureJni(String sInputPath, String sOutputPath, String sTextPath);
	public native String colorAverageRGBJni(int R1, int R2, int R3, int G1, int G2, int G3, int B1, int B2, int B3, int nType,int nCount); 
	public native String colorAverageRGBSJni(String sRGB, int nType,int nCount); 
	public native double skinFutureMelaninJni(String sInputPath, String sOutputPath);
	public native double skinElasticityJni(int nAge, int nMoisture);
	public native double appTestJni(String sInputPath, String sOutputPath);
	public native double getThreeDTextureJni(String sInputPath, String sOutputPath);
	
	////////////////////////////////////2022-09-23 UPDATED BY SHU LI ////////////////////////////////////////////
	//
	//
	public native double CNDPskinSensitivityRedness111Jni(String sInputPath, String sOutputPath);
	
	public native String CNDPskinWrinkles217Jni(String sInputPath, String sTotalOutputPath, String sUltraFineOutputPath, String sFineOutputPath, String sDeepOutputPath, String sUltradeepOutputPath);
	public native double CNDPskinShine100Jni(String sInputPath, String sOutputPath);
	public native double CNDPskinSebum125Jni(String sInputPath, String sOutputPath);
	public native double CNDPskinSpots212Jni(String sInputPath, String sOutputPath);
	
	public native double CNDPskinImpurities208Jni(String sInputPath, String sOutputPath);
	public native double CNDPskinKeratin128Jni(String sInputPath, String sOutputPath);
	public native double CNDPskinPores208Jni(String sInputPath, String sOutputPath);
	public native String CNDPskinTone207Jni(String sInputForeheadPath, String sInputCheekPath);	// Shu Li, updated on 2024-03-15
	public native String CNDPskinToneWithNeck207Jni(String sInputForeheadPath, String sInputCheekPath, String sInputNeckPath); 	// Shu Li, added on 2024-03-15
	public native double matchToneWithChowisShade100Jni(int redValue, int greenValue, int blueValue); 	// Shu LLi, added on 2024-03-15
	public native double FizpatrickSG102Jni(String sInputPath, String sOutputPath);	// use this for skin group selection in user profile.
	
	
	//////////////////////////////////// 2024-02-05 DermoMedical APP Java API ////////////////////////////////////////////
	//
	//
	public native String CNDPskinSensitivityRedness112Jni(String sInputPath, String sTotalOutputPath, String sPinkOutputPath, String sRedOutputPath);
	public native String CNDPskinSpots213Jni(String sInputPath, String sTotalOutputPath, String sYellowOutputPath, String sOrangeOutputPath, String sGreenOutputPath);
	// NOTICE: USE WRINKLESS AI MEASUREMENT IN MEDICAL APP.
	// NOTICE: USE PORES AI MEASUREMENT IN MEDICAL APP.
	
	////////////////////////////////////2022-10-05 UPDATED BY SHU LI ////////////////////////////////////////////
	//
	//
    public native double computationCNDPSkinSensitivity101Jni(double[] analysisResults, double questionnaireScore, int resultCount);
    public native double computationCNDPSkinWrinkles101Jni(double[] analysisResults, double questionnaireScore, int resultCount);
    public native double computationCNDPSkinShine100Jni(double[] analysisResults, int resultCount);
    public native double computationCNDPSkinSebum101Jni(double[] analysisResults, double questionnaireScore, int resultCount);
    public native double computationCNDPSkinSpots101Jni(double[] analysisResults, double questionnaireScore, int resultCount);
    public native double computationCNDPSkinImpurities100Jni(double[] analysisResults, int resultCount);
    public native double computationCNDPSkinKeratin100Jni(double[] analysisResults, int resultCount);
    public native double computationCNDPSkinPores100Jni(double[] analysisResults, int resultCount);
    public native double computeSkinAge100Jni(double wrinkleScore, double pigmentationSpotsScore, double realBiologicalAge);	// 2023-03-17
    
    public native String skinCondition100Jni(double mScoreT, double sScoreT, double mScoreU, double sScoreU, double QAScore);		// 2023-10-06
    
	////////////////////////////////////2022-10-05 UPDATED BY SHU LI ////////////////////////////////////////////
	//
	//
    //public native double questionnaireCNDPSkinSensitivity100Jni(String answers);
    public native String questionnaireCNDPSkin102Jni(String answers);
    
    
	////////////////////////////////////2023-07-27 UPDATED BY SHU LI ////////////////////////////////////////////
	//
	//
    //public native double getSkinWrinklesAIAnalyzedResultImgJni(String inputOriginalImgPath, String inputMaskImgPath, String outputResultImgPath);
    //public native double getSkinPoresAIAnalyzedResultImgJni(String sInputOriginalImgPath, String sBlackheadMaskImgPath, String sCloggedPoresMaskImgPath, String sRegularPoresMaskImgPath, String sOutputPath);
    public native double CND25WrinklesSG1234PreProcessingJni(String sInputPath, String sOutputPath);
    public native double CND25WrinklesSG56PreProcessingJni(String sInputPath, String sOutputPath);
    public native double CND25PoresSG1234PreProcessingJni(String sInputPath, String sOutputPath);
    public native double CND25PoresSG56PreProcessingJni(String sInputPath, String sOutputPath);
    public native double poresIPMaskingJni(String sInputPath, String smaskInputPath, String sOutputPath);
    public native double poresPostProcessingJni(String sInputPath);
    public native String wrinklesPostProcessingJni(String sInputOriginalImgPath, String inputMaskImgPath, String totalWrinkleOutputPath, String ultraFineOutputPath, String fineOutputPath, String deepOutputPath, String ultraDeepOutputPath);
}
