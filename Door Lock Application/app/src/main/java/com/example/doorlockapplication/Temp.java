package com.example.doorlockapplication;

import android.util.Log;

import androidx.annotation.Nullable;

import com.samsung.android.sdk.coldwallet.ScwCoinType;
import com.samsung.android.sdk.coldwallet.ScwService;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java8.util.concurrent.CompletableFuture;

import static java.util.Collections.singletonList;

public class Temp {
    public static final String CONTRACT_ADDRESS = "0x5dFcDc09966250F5ec678d1D28695211CEd672bb";

    public static Function createGetPostCountSmartContractCall() {
        return new Function("openDoor"
                , Collections.emptyList()
                , singletonList(new TypeReference<Bool>() {
        }));
    }

    public static void makeTrx(String address) {
        try {
            Function func = createGetPostCountSmartContractCall();
            String data = FunctionEncoder.encode(func);

            String hdPath = ScwService.getHdPath(ScwCoinType.ETH, 0);


            RawTransaction unsignedTransaction = RawTransaction.createTransaction(RemoteManager.getInstance().getNonce(address), Convert.toWei("400", Convert.Unit.GWEI).toBigInteger()
                    , new BigInteger("41000"), CONTRACT_ADDRESS, data);

            byte[] byteArr = TransactionEncoder.encode(unsignedTransaction);

            ScwService.getInstance().signEthTransaction(new ScwService.ScwSignEthTransactionCallback() {
                @Override
                public void onSuccess(byte[] bytes) {
                    sendTransaction(bytes);
                }
                @Override
                public void onFailure(int i, @Nullable String s) {
                    Log.e("signEth", s);
                }
            }, byteArr,hdPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getEthereumAddress() {
        ScwService.ScwGetAddressListCallback callback =
                new ScwService.ScwGetAddressListCallback() {
                    @Override
                    public void onSuccess(List<String> addressList) {
                        Log.i("hello", addressList.get(0)); //public address
                        makeTrx(addressList.get(0));
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMessage) {
                        //handle errors

                    }
                };

        String hdPath = ScwService.getHdPath(ScwCoinType.ETH, 0);

        ArrayList<String> hdPathList = new ArrayList<>();
        hdPathList.add(hdPath);

        ScwService.getInstance().getAddressList(callback, hdPathList);
    }

    public static void sendTransaction(byte[] signedTransaction) {
        try {
            String signedTxHex = Numeric.toHexString(signedTransaction);
            Log.d("KeystoreManager", "signed tx + " + signedTxHex);
            RemoteManager.getInstance().sendRawTransaction(signedTxHex);
        } catch (Exception e) {
            Log.e("SendRawTransaction", e.getMessage());
        }

    }


}
