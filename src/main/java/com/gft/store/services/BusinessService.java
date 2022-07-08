package com.gft.store.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.gft.store.models.entities.Purchase;
import com.gft.store.models.entities.PurchaseItem;
import com.gft.store.models.entities.Sale;
import com.gft.store.models.entities.SaleItem;
import com.gft.store.models.forms.PurchaseOrder;
import com.gft.store.models.forms.SaleOrder;
import com.gft.store.models.forms.TradeOrderItem;
import com.gft.store.repositories.BranchRepository;
import com.gft.store.repositories.ClientRepository;
import com.gft.store.repositories.ProductRepository;
import com.gft.store.repositories.ProviderRepository;
import com.gft.store.repositories.PurchaseItemRepository;
import com.gft.store.repositories.SaleItemRepository;
import com.gft.store.repositories.PurchaseRepository;
import com.gft.store.repositories.SaleRepository;
import com.gft.store.repositories.UserRepository;

@Service
public class BusinessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private PurchaseItemRepository tradeItemRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    // BUY LOGIC

    public PurchaseOrder makeBuy(PurchaseOrder order) {
        var userBranch = userRepository
                .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .get()
                .getBranch();

        var provider = providerRepository.findById(order.getProvider_id());

        if (provider.isPresent()) {
            var buy = new Purchase(userBranch, provider.get());
            purchaseRepository.save(buy);

            buy.setItens(convertItensForPurchase(order.getItens()));
            purchaseRepository.save(buy);
            return order;
        }
        return null;
    }

    public List<PurchaseItem> convertItensForPurchase(List<TradeOrderItem> list) {

        List<PurchaseItem> listOfItens = new ArrayList<>();

        for (TradeOrderItem item : list) {
            if (productRepository.findById(item.getProduct_id()).isPresent()) {
                var actualProduct = productRepository.findById(item.getProduct_id()).get();
                listOfItens.add(new PurchaseItem(actualProduct, item.getAmount(), item.getTrade_value()));
            }
        }
        tradeItemRepository.saveAll(listOfItens);
        return listOfItens;
    }

    public Page<Purchase> getAllPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    // SELL LOGIC

    public SaleOrder makeSale(SaleOrder order) {
        var branch = branchRepository.findById(order.getBranch_id());
        var client = clientRepository.findById(order.getClient_id());

        if (client.isPresent() && branch.isPresent()) {
            var sale = new Sale(branch.get(), client.get());
            saleRepository.save(sale);

            sale.setItens(convertItensForSale(order.getItens()));
            saleRepository.save(sale);
            return order;
        }
        return null;
    }

    public List<SaleItem> convertItensForSale(List<TradeOrderItem> list) {

        List<SaleItem> listOfItens = new ArrayList<>();

        for (TradeOrderItem item : list) {
            if (productRepository.findById(item.getProduct_id()).isPresent()) {
                var actualProduct = productRepository.findById(item.getProduct_id()).get();
                listOfItens.add(new SaleItem(actualProduct, item.getAmount(), item.getTrade_value()));
            }
        }

        saleItemRepository.saveAll(listOfItens);
        return listOfItens;
    }

    public Page<Sale> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

}
