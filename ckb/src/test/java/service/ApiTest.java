package service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.*;
import org.nervos.ckb.service.Api;
import org.nervos.ckb.type.*;
import org.nervos.ckb.type.cell.CellOutputWithOutPoint;
import org.nervos.ckb.type.cell.CellTransaction;
import org.nervos.ckb.type.cell.CellWithStatus;
import org.nervos.ckb.type.cell.LiveCell;
import org.nervos.ckb.type.transaction.Transaction;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTest {

  private Api api;

  @BeforeAll
  public void init() {
    api = new Api("http://localhost:8114", false);
  }

  @Test
  public void testGetBlockByNumber() throws IOException {
    Block block = api.getBlockByNumber("0x1");
    Assertions.assertNotNull(block);
  }

  @Test
  public void testGetBlockHashByNumber() throws IOException {
    String blockHash = api.getBlockHash("0x1");
    Assertions.assertNotNull(blockHash);
  }

  @Test
  public void testGetCellbaseOutputCapacityDetails() throws IOException {
    String blockHash = api.getBlockHash("0x1");
    CellbaseOutputCapacity cellbaseOutputCapacity = api.getCellbaseOutputCapacityDetails(blockHash);
    Assertions.assertNotNull(cellbaseOutputCapacity);
  }

  @Test
  public void testBlockAndTransaction() throws IOException {
    String blockHash = api.getBlockHash("0x1");
    Block block = api.getBlock(blockHash);
    Assertions.assertNotNull(block);
    Assertions.assertNotNull(block.header);
  }

  @Test
  public void testTransaction() throws IOException {
    String transactionHash = api.getBlockByNumber("0x1").transactions.get(0).hash;
    Transaction transaction = api.getTransaction(transactionHash).transaction;
    Assertions.assertNotNull(transaction);
  }

  @Test
  public void testGetTipHeader() throws IOException {
    Header header = api.getTipHeader();
    Assertions.assertNotNull(header);
  }

  @Test
  public void testGetTipBlockNumber() throws IOException {
    BigInteger blockNumber = api.getTipBlockNumber();
    Assertions.assertNotNull(blockNumber.toString());
  }

  @Test
  public void testGetCurrentEpoch() throws IOException {
    Epoch epoch = api.getCurrentEpoch();
    Assertions.assertNotNull(epoch);
  }

  @Test
  public void testGetEpochByNumber() throws IOException {
    Epoch epoch = api.getEpochByNumber("0");
    Assertions.assertNotNull(epoch);
  }

  @Test
  public void testGetHeader() throws IOException {
    String blockHash = api.getBlockHash("0x1");
    Header header = api.getHeader(blockHash);
    Assertions.assertNotNull(header);
  }

  @Test
  public void testGetHeaderByNumber() throws IOException {
    Header header = api.getHeaderByNumber("0x1");
    Assertions.assertNotNull(header);
  }

  @Test
  public void localNodeInfo() throws IOException {
    NodeInfo nodeInfo = api.localNodeInfo();
    Assertions.assertNotNull(nodeInfo);
  }

  @Test
  public void getPeers() throws IOException {
    List<NodeInfo> peers = api.getPeers();
    Assertions.assertNotNull(peers);
  }

  @Test
  public void testSetBan() throws IOException {
    BannedAddress bannedAddress =
        new BannedAddress("192.168.0.2", "insert", "1840546800000", true, "test set_ban rpc");
    String banResult = api.setBan(bannedAddress);
    Assertions.assertNull(banResult);
  }

  @Test
  public void testGetBannedAddress() throws IOException {
    List<BannedResultAddress> bannedAddresses = api.getBannedAddress();
    Assertions.assertNotNull(bannedAddresses);
  }

  @Test
  public void txPoolInfo() throws IOException {
    TxPoolInfo txPoolInfo = api.txPoolInfo();
    Assertions.assertNotNull(txPoolInfo);
  }

  @Test
  public void testGetBlockchainInfo() throws IOException {
    BlockchainInfo blockchainInfo = api.getBlockchainInfo();
    Assertions.assertNotNull(blockchainInfo);
  }

  @Test
  public void testGetPeersState() throws IOException {
    List<PeerState> peerStates = api.getPeersState();
    Assertions.assertNotNull(peerStates);
  }

  @Test
  public void testGetCellsByLockHash() throws IOException {
    List<CellOutputWithOutPoint> cellOutputWithOutPoints =
        api.getCellsByLockHash(
            "0xecaeea8c8581d08a3b52980272001dbf203bc6fa2afcabe7cc90cc2afff488ba", "0", "100");
    Assertions.assertTrue(cellOutputWithOutPoints.size() > 0);
  }

  @Test
  public void testGetLiveCell() throws IOException {
    CellWithStatus cellWithStatus =
        api.getLiveCell(
            new OutPoint("0xde7ac423660b95df1fd8879a54a98020bcbb30fc9bfcf13da757e99b30effd8d", "0"),
            true);
    Assertions.assertNotNull(cellWithStatus);
  }

  @Test
  public void testGetLiveCellWithData() throws IOException {
    CellWithStatus cellWithStatus =
        api.getLiveCell(
            new OutPoint("0xde7ac423660b95df1fd8879a54a98020bcbb30fc9bfcf13da757e99b30effd8d", "0"),
            true);
    Assertions.assertNotNull(cellWithStatus.cell.data);
  }

  @Test
  public void testGetLiveCellWithoutData() throws IOException {
    CellWithStatus cellWithStatus =
        api.getLiveCell(
            new OutPoint("0xde7ac423660b95df1fd8879a54a98020bcbb30fc9bfcf13da757e99b30effd8d", "0"),
            false);
    Assertions.assertNull(cellWithStatus.cell.data);
  }

  @Test
  public void testSendTransaction() throws IOException {
    String transactionHash =
        api.sendTransaction(
            new Transaction(
                "0",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()));
    Assertions.assertNotNull(transactionHash);
  }

  @Test
  public void testDryRunTransaction() throws IOException {
    Cycles cycles =
        api.dryRunTransaction(
            new Transaction(
                "0",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()));
    Assertions.assertNotNull(cycles);
  }

  @Test
  public void testComputeTransactionHash() throws IOException {
    String transactionHash =
        api.computeTransactionHash(
            new Transaction(
                "0",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()));
    Assertions.assertNotNull(transactionHash);
  }

  @Test
  public void testEstimateFeeRate() {
    Assertions.assertThrows(IOException.class, () -> api.estimateFeeRate("0x0a"));
  }

  @Test
  public void testIndexLockHash() throws IOException {
    LockHashIndexState lockHashIndexState =
        api.indexLockHash("0x59d90b1718471f5802de59501604100a5e3b463865cdfe56fa70ed23865ee32e");
    Assertions.assertNotNull(lockHashIndexState);
  }

  @Test
  public void testIndexLockHashWithBlockNumber() throws IOException {
    LockHashIndexState lockHashIndexState =
        api.indexLockHash(
            "0x59d90b1718471f5802de59501604100a5e3b463865cdfe56fa70ed23865ee32e", "0");
    Assertions.assertNotNull(lockHashIndexState);
  }

  @Test
  public void testDeindexLockHash() throws IOException {
    List<String> lockHashs =
        api.deindexLockHash("0x59d90b1718471f5802de59501604100a5e3b463865cdfe56fa70ed23865ee32e");
    Assertions.assertNull(lockHashs);
  }

  @Test
  public void testGetLockHashIndexStates() throws IOException {
    List<LockHashIndexState> lockHashIndexStates = api.getLockHashIndexStates();
    Assertions.assertNotNull(lockHashIndexStates);
  }

  @Test
  public void testGetLiveCellsByLockHash() throws IOException {
    List<LiveCell> liveCells =
        api.getLiveCellsByLockHash(
            "0xecaeea8c8581d08a3b52980272001dbf203bc6fa2afcabe7cc90cc2afff488ba",
            "0",
            "100",
            false);
    Assertions.assertNotNull(liveCells);
  }

  @Test
  public void testGetTransactionsByLockHash() throws IOException {
    List<CellTransaction> cellTransactions =
        api.getTransactionsByLockHash(
            "0xecaeea8c8581d08a3b52980272001dbf203bc6fa2afcabe7cc90cc2afff488ba",
            "0",
            "100",
            false);
    Assertions.assertNotNull(cellTransactions);
  }
}
